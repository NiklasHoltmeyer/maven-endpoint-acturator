package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndpointExecutionInfo;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RestControllerEndpoint(id = "executeTime")
public class ExecutetimeActurator {
    public static List list = Collections.synchronizedList(new ArrayList<EndpointExecutionInfo>());

    @GetMapping("/")
    public List zeze(){return list;}
}
