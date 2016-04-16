package ua.org.gostroy.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import ua.org.gostroy.domain.entity.Trip;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
public abstract class TripService {

    @Value("${goRest.parse.userAgent}")
    private String userAgent;

    public List<Trip> parseUrl(String parseUrl, String parseStartPath) {

        List<Trip> list = new ArrayList<>();
        Document doc = null;
        try {
            String url = new URI(parseUrl + parseStartPath).toASCIIString();
            doc = Jsoup.connect(url).userAgent(userAgent).get();

            parsePage(doc, list, parseUrl);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return list;
    }

    protected abstract void parsePage(Document doc, List<Trip> list, String url) throws IOException, URISyntaxException;

    protected abstract void parseEntity(String urlForEntity, List<Trip> list) throws IOException, URISyntaxException;
}
