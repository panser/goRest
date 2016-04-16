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

    public static Elements jsoupSelectListWithNullPointerCheck(Element element, String selector, String selectorInfo) {
        Elements elements = element.select(selector);
        if (elements.size() == 0) {
            log.warn(String.format(" --- exception in jsoup select method during: %1s --- ", selectorInfo));
            log.info(String.format(" ---  we didn't find selector \"%1s\" --- ", selector));
            log.debug(String.format(" ---  in element: --- \n %1s", element.toString()));
        }

        return elements;
    }

    public static Element jsoupSelectWithNullPointerCheck(Element element, String selector, String selectorInfo) {
        Elements elements = element.select(selector);
        if (elements.size() == 0) {
            log.warn(String.format(" --- exception in jsoup select method during: %1s --- ", selectorInfo));
            log.info(String.format(" ---  we didn't find selector \"%1s\" --- ", selector));
            log.debug(String.format(" ---  in element: --- \n %1s", element.toString()));
            return null;
        }

        return elements.first();
    }

    public static Long parseJsoupLong(String longInsideString, Integer index) {

        String[] stringParts = longInsideString.split(" ");
        String longForParse = stringParts[index];

        return Long.parseLong(longForParse);
    }

    public static Date parseJsoupDate(String dateInsideString, Integer index, String format) {
        Date result = null;

        String[] stringParts = dateInsideString.split(" ");
        String dateForParse = stringParts[index];

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            result = formatter.parse(dateForParse);
        } catch (ParseException e) {
            log.error(String.format("Can't parse date: %1s by format %2s by index %3s", dateForParse, format, index));
        }

        return result;
    }

}
