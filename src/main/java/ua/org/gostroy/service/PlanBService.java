package ua.org.gostroy.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ua.org.gostroy.domain.entity.Trip;
import ua.org.gostroy.util.JsoupUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
//@Service
public class PlanBService extends TripService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${goRest.parse.userAgent}")
    private String userAgent;

    @Value("${goRest.parse.planB.selector.list.selector}")
    private String listSelector;
    @Value("${goRest.parse.planB.selector.list.info}")
    private String listInfo;

    @Value("${goRest.parse.planB.selector.urlForEntity.selector}")
    private String urlForEntitySelector;
    @Value("${goRest.parse.planB.selector.urlForEntity.info}")
    private String urlForEntityInfo;

    @Value("${goRest.parse.planB.selector.entity.selector}")
    private String entitySelector;
    @Value("${goRest.parse.planB.selector.entity.info}")
    private String entityInfo;

    @Value("${goRest.parse.planB.selector.entityTitle.selector}")
    private String entityTitleSelector;
    @Value("${goRest.parse.planB.selector.entityTitle.info}")
    private String entityTitleInfo;

    @Value("${goRest.parse.planB.selector.entityLogoUrl.selector}")
    private String entityLogoUrlSelector;
    @Value("${goRest.parse.planB.selector.entityLogoUrl.info}")
    private String entityLogoUrlInfo;

    @Value("${goRest.parse.planB.selector.entityDateStart.selector}")
    private String entityDateStartSelector;
    @Value("${goRest.parse.planB.selector.entityDateStart.format}")
    private String entityDateStartFormat;
    @Value("${goRest.parse.planB.selector.entityDateStart.index}")
    private Integer entityDateStartIndex;
    @Value("${goRest.parse.planB.selector.entityDateStart.info}")
    private String entityDateStartInfo;


    @Override
    protected void parsePage(Document doc, List<Trip> list, String url) throws IOException, URISyntaxException {
        if (doc == null) return;

        Elements elements = JsoupUtils.jsoupSelectListWithNullPointerCheck(doc, listSelector, listInfo);
        for (Element element : elements) {
            Element urlElement = JsoupUtils.jsoupSelectWithNullPointerCheck(element, urlForEntitySelector, urlForEntityInfo);
            String urlForEntity = urlElement.attr("href");

            parseEntity(url + urlForEntity, list);
        }
    }

    @Override
    protected void parseEntity(String urlForEntity, List<Trip> list) throws IOException, URISyntaxException {
        Document doc = null;
        try {
            String urlEntity = new URI(urlForEntity).toASCIIString();
            doc = Jsoup.connect(urlEntity).userAgent(userAgent).get();
        } catch (NullPointerException e) {
            log.error("NullPointerException in parsePage(), continue ...");
        }
        if (doc == null) return;
        Trip entity = new Trip();

        try {
            Element element = JsoupUtils.jsoupSelectWithNullPointerCheck(doc, entitySelector, entityInfo + " in " + urlForEntity);

            Element title = JsoupUtils.jsoupSelectWithNullPointerCheck(element, entityTitleSelector, entityTitleInfo + " in " + urlForEntity);
            if (title != null) {
                entity.setTitle(title.text());
            }
            Element date = JsoupUtils.jsoupSelectWithNullPointerCheck(element, entityDateStartSelector, entityDateStartInfo + " in " + urlForEntity);
            if (date != null) {
                Date dateParsed = JsoupUtils.parseJsoupDate(date.text(), entityDateStartIndex, entityDateStartFormat);
                entity.setDateStart(dateParsed);
            }
            Element logoUrl = JsoupUtils.jsoupSelectWithNullPointerCheck(element, entityLogoUrlSelector, entityLogoUrlInfo + " in " + urlForEntity);
            if (logoUrl != null) {
                entity.setLogoUrl(title.attr("href"));
            }

            list.add(entity);
        } catch (NullPointerException e) {
            log.error("NullPointerException in parseEntity(), continue ...");
        }
    }

}
