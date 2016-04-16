package ua.org.gostroy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.gostroy.domain.confproperties.ParseSite;
import ua.org.gostroy.internal.ConvertJsonConfToPojo;

/**
 * Created by Sergey on 4/14/2016.
 */
@Service
public class ProjectService {

    @Autowired
    ConvertJsonConfToPojo convertJsonConfToPojo;

    public void loadConfigurationToMemory() {

        ParseSite parseSite = convertJsonConfToPojo.convert();
    }
}
