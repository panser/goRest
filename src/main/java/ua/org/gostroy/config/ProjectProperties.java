package ua.org.gostroy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.confproperties.Parse;

/**
 * Created by Sergey on 4/14/2016.
 */
@ConfigurationProperties(prefix = "goRest", ignoreUnknownFields = false)
@Service
public class ProjectProperties {

    public static Parse parse;
//    public List<ParseSite> parseSite;

    public Parse getParse() {
        return parse;
    }

    public void setParse(Parse parse) {
        ProjectProperties.parse = parse;
    }

//    public List<ParseSite> getParseSite() {
//        return parseSite;
//    }
//
//    public void setParseSite(List<ParseSite> parseSite) {
//        this.parseSite = parseSite;
//    }
}
