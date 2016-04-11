package ua.org.gostroy.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergey on 4/11/2016.
 */
public class JsoupUtils {

    private final static Logger log = LoggerFactory.getLogger(JsoupUtils.class);

    public static Elements jsoapSelectWithNullPointerCheck(Element element, String selector) {
        Elements elements = element.select(selector);
        if (elements.size() == 0) {
            log.error(" --- exception in jsoap select method: we didn't find selector\"" + selector + "\" --- ");
            log.debug(" --- in element: --- \n" + element.toString());
        }

        return elements;
    }

    public static Date parseJsoupDateByFormat(String dateInString, String format) {
        Date result = null;

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            result = formatter.parse(dateInString);

        } catch (ParseException e) {
            log.error(String.format("Can't parse date: %1s by format %2s", dateInString, format));
        }

        return result;
    }

}
