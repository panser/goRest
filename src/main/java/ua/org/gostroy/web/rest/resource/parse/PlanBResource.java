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
import ua.org.gostroy.service.PlanBService;

import java.util.List;

/**
 * Created by Sergey on 4/11/2016.
 */
@RestController
@RequestMapping("/parse/planb")
public class PlanBResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${goRest.parse.planB.url}")
    private String parseUrl;

    @Autowired
    PlanBService planBService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Trip> list() {

        List<Trip> list = planBService.parseUrl(parseUrl);
        return list;
    }

}
