package ua.org.gostroy.domain.confproperties;

/**
 * Created by Sergey on 4/14/2016.
 */
public class Parse {

    private String userAgent;
    private ParseSite parseSite;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public ParseSite getParseSite() {
        return parseSite;
    }

    public void setParseSite(ParseSite parseSite) {
        this.parseSite = parseSite;
    }
}
