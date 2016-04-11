package ua.org.gostroy.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.entity.Trip;
import ua.org.gostroy.util.JsoupUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
@Service
public class PlanBService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${goRest.parse.userAgent}")
    private String userAgent;
    @Value("${goRest.parse.planB.selector.listSelector}")
    private String listSelector;
    @Value("${goRest.parse.planB.selector.urlForEntitySelector}")
    private String urlForEntitySelector;
    @Value("${goRest.parse.planB.selector.entitySelector}")
    private String entitySelector;
    @Value("${goRest.parse.planB.selector.entityTitleSelector}")
    private String entityTitleSelector;
    @Value("${goRest.parse.planB.selector.entityDate.selector}")
    private String entityDateSelector;
    @Value("${goRest.parse.planB.selector.entityDate.format}")
    private String entityDateFormat;
    @Value("${goRest.parse.planB.selector.entityDate.index}")
    private Integer entityDateIndex;
    @Value("${goRest.parse.planB.selector.entityImageUrlSelector}")
    private String entityImageUrlSelector;

    public List<Trip> parseUrl(String url) {

        List<Trip> list = new ArrayList<>();
        if (url == null || url.isEmpty()) {
            return list;
        }

        Document doc = null;
        try {
            url = new URI(url).toASCIIString();
            doc = Jsoup.connect(url).userAgent(userAgent).get();

            parsePage(doc, list, url);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void parsePage(Document doc, List<Trip> list, String url) throws IOException, URISyntaxException {
        if (doc == null) return;

        Elements elements = doc.select(listSelector);
        for (Element element : elements) {
            try {
                Element urlElement = JsoupUtils.jsoapSelectWithNullPointerCheck(element, urlForEntitySelector).first();
                String urlForEntity = urlElement.attr("href");

                String urlEntity = new URI(urlForEntity).toASCIIString();
                doc = Jsoup.connect(url + urlEntity).userAgent(userAgent).get();

                parseEntity(doc, list);
            } catch (NullPointerException e) {
                log.error("NullPointerException in parsePage(), continue ...");
            }
        }
    }

    private void parseEntity(Document doc, List<Trip> list) {
        if (doc == null) return;
        Trip entity = new Trip();

        try {
            Element element = JsoupUtils.jsoapSelectWithNullPointerCheck(doc, entitySelector).first();

            Elements title = JsoupUtils.jsoapSelectWithNullPointerCheck(element, entityTitleSelector);
            Elements date = JsoupUtils.jsoapSelectWithNullPointerCheck(element, entityDateSelector);
            Elements imageUrl = JsoupUtils.jsoapSelectWithNullPointerCheck(element, entityImageUrlSelector);

            Date dateParsed = null;
            if (date.size() != 0) {
                String dateNotParsed = date.text();
                String[] dateParts = dateNotParsed.split(" ");
                dateParsed = JsoupUtils.parseJsoupDateByFormat(dateParts[entityDateIndex], entityDateFormat);
            }

            entity.setTitle((title.size() != 0) ? title.text() : "");
            entity.setDateStart(dateParsed);
            entity.setImageUrl((imageUrl.size() != 0) ? imageUrl.attr("href") : "");

            list.add(entity);
        } catch (NullPointerException e) {
            log.error("NullPointerException in parseEntity(), continue ...");
        }
    }

}
