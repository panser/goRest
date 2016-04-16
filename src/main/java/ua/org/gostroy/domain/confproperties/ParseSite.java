package ua.org.gostroy.domain.confproperties;

import java.util.List;

/**
 * Created by Sergey on 4/14/2016.
 */
public class ParseSite {

    private String name;
    private String startPath;
    private List<Entity> selector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartPath() {
        return startPath;
    }

    public void setStartPath(String startPath) {
        this.startPath = startPath;
    }

    public List<Entity> getSelector() {
        return selector;
    }

    public void setSelector(List<Entity> selector) {
        this.selector = selector;
    }
}
