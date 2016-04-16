package ua.org.gostroy.web.rest.resource.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gostroy.domain.entity.Trip;
import ua.org.gostroy.service.KuluarpohodService;
import ua.org.gostroy.service.PlanBService;

import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
@RestController
@RequestMapping("/parse")
public class TripResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${goRest.parse.planB.url}")
    private String parsePlanbUrl;
    @Value("${goRest.parse.planB.startPath}")
    private String parsePlanbStartPath;

    @Value("${goRest.parse.kuluarpohod.url}")
    private String parseKuluarpohodUrl;
    @Value("${goRest.parse.kuluarpohod.startPath}")
    private String parseKuluarpohodStartPath;


    @Autowired
    PlanBService planBService;
    @Autowired
    KuluarpohodService kuluarpohodService;


    @RequestMapping(value = "/planb", method = RequestMethod.GET)
    @ResponseBody
    public List<Trip> listPlanB() {

        List<Trip> list = planBService.parseUrl(parsePlanbUrl, parsePlanbStartPath);
        return list;
    }

    @RequestMapping(value = "/kuluarpohod", method = RequestMethod.GET)
    @ResponseBody
    public List<Trip> listKuluarpohod() {

        List<Trip> list = kuluarpohodService.parseUrl(parseKuluarpohodUrl, parseKuluarpohodStartPath);
        return list;
    }
}
