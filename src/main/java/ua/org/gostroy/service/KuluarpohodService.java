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
import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
@Service
public class KuluarpohodService extends TripService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${goRest.parse.userAgent}")
    private String userAgent;

    @Value("${goRest.parse.kuluarpohod.selector.list.selector}")
    private String listSelector;
    @Value("${goRest.parse.kuluarpohod.selector.list.info}")
    private String listInfo;

    @Value("${goRest.parse.kuluarpohod.selector.urlForEntity.selector}")
    private String urlForEntitySelector;
    @Value("${goRest.parse.kuluarpohod.selector.urlForEntity.info}")
    private String urlForEntityInfo;

    @Value("${goRest.parse.kuluarpohod.selector.entity.selector}")
    private String entitySelector;
    @Value("${goRest.parse.kuluarpohod.selector.entity.info}")
    private String entityInfo;

    @Value("${goRest.parse.kuluarpohod.selector.entityTitle.selector}")
    private String entityTitleSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityTitle.info}")
    private String entityTitleInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityLogoUrl.selector}")
    private String entityLogoUrlSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityLogoUrl.info}")
    private String entityLogoUrlInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityLocation.selector}")
    private String entityLocationSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityLocation.info}")
    private String entityLocationInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityKm.selector}")
    private String entityKmSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityKm.info}")
    private String entityKmInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityLevel.selector}")
    private String entityLevelSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityLevel.info}")
    private String entityLevelInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityCost.selector}")
    private String entityCostSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityCost.info}")
    private String entityCostInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityType.selector}")
    private String entityTypeSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityType.info}")
    private String entityTypeInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityDescription.selector}")
    private String entityDescriptionSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityDescription.info}")
    private String entityDescriptionInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityCompanyName.selector}")
    private String entityCompanyNameSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityCompanyName.info}")
    private String entityCompanyNameInfo;
    @Value("${goRest.parse.kuluarpohod.selector.entityUrl.selector}")
    private String entityUrlSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityUrl.info}")
    private String entityUrlInfo;


    @Value("${goRest.parse.kuluarpohod.selector.entityDateStart.selector}")
    private String entityDateStartSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateStart.format}")
    private String entityDateStartFormat;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateStart.index}")
    private Integer entityDateStartIndex;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateStart.info}")
    private String entityDateStartInfo;

    @Value("${goRest.parse.kuluarpohod.selector.entityDateFinish.selector}")
    private String entityDateFinishSelector;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateFinish.format}")
    private String entityDateFinishFormat;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateFinish.index}")
    private Integer entityDateFinishIndex;
    @Value("${goRest.parse.kuluarpohod.selector.entityDateFinish.info}")
    private String entityDateFinishInfo;


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
            Element logoUrl = JsoupUtils.jsoupSelectWithNullPointerCheck(element, entityLogoUrlSelector, entityLogoUrlInfo + " in " + urlForEntity);
            if (logoUrl != null) {
                entity.setLogoUrl(title.attr("href"));
            }
            Element location = JsoupUtils.jsoupSelectWithNullPointerCheck(doc, entityLocationSelector, entityLocationInfo + " in " + urlForEntity);
            if (location != null) {
                entity.setLocation(title.attr("content"));
            }

//            Elements date = JsoupUtils.jsoupSelectWithNullPointerCheck(element, entityDateSelector, entityDateInfo + " in " + urlForEntity);
//            Date dateParsed = null;
//            if (date.size() != 0) {
//                String dateNotParsed = date.text();
//                String[] dateParts = dateNotParsed.split(" ");
//                dateParsed = JsoupUtils.parseJsoupDate(dateParts[entityDateIndex], entityDateFormat);
//            }
//
//            entity.setDateStart(dateParsed);
//            entity.setLogoUrl((imageUrl.size() != 0) ? imageUrl.attr("href") : "");

            list.add(entity);
        } catch (NullPointerException e) {
            log.error("NullPointerException in parseEntity(), continue ...");
        }
    }

}
