package ua.org.gostroy.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.confproperties.ParseSite;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey on 4/14/2016.
 */
@Service
public class ConvertJsonConfToPojo {

    public ParseSite convert() {

        File jsonFile = new File("classpath://parseSites.json");
        ObjectMapper mapper = new ObjectMapper();

        ParseSite parseSite = null;
        try {
            parseSite = mapper.readValue(jsonFile, ParseSite.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseSite;
    }
}
