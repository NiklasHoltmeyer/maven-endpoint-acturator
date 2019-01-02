package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.EndpointExecutionInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndpointGroupInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.GroupExecutionInfos;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@RestControllerEndpoint(id = "executeinfo")
public class ExecuteInfoActurator {
    public static List list = Collections.synchronizedList(new ArrayList<EndpointExecutionInfo>());
    public static String[] endPointBlacklist = {"/error", "/actuator"}; //lowerCase

    @GetMapping("/")
    public List<EndpointGroupInfo> zeze(){
        return new GroupExecutionInfos()
                .group(getEndpointExecutionInfos());
    }

    @GetMapping("/reset")
    public String reset(){
        list = Collections.synchronizedList(new ArrayList<EndpointExecutionInfo>());
        return "ok";
    }

    private List<EndpointExecutionInfo> getEndpointExecutionInfos(){
        return (List<EndpointExecutionInfo>)list.stream()
                .filter(this::blackListFilter)
                .collect(Collectors.toList());
    }

    private boolean blackListFilter(Object o) {
        EndpointExecutionInfo endpointExecutionInfo = (EndpointExecutionInfo) o;
        if(endpointExecutionInfo==null) return true;
        String url = endpointExecutionInfo.getUrl().toLowerCase(Locale.GERMAN);
        if(url == null) return true;
        for(String bL : endPointBlacklist){
            if(url.contains(bL)) return false;
        }
        return true;
    }
}

