package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.EndpointExecutionInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndpointGroupInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupExecutionInfos {
    public List<EndpointGroupInfo> group(List<EndpointExecutionInfo> endpointExecutionInfoList){
        List<EndpointGroupInfo> result = new ArrayList<>();
        Map<String, Map<String, List<EndpointExecutionInfo>>> collection = endpointExecutionInfoList.stream()
                .collect(
                        Collectors.groupingBy(EndpointExecutionInfo::getUrl,
                                Collectors.groupingBy(EndpointExecutionInfo::getMethod))
                );

        for(String url : collection.keySet()){
            // Map(url, Map(method, listEndpointExecutionInfo))
            Map<String, List<EndpointExecutionInfo>> stringListMap = collection.get(url);
            for(String method : stringListMap.keySet()){
                List<EndpointExecutionInfo> list = stringListMap.get(method);
                EndpointGroupInfo endpointGroupInfo = new EndpointGroupInfo(url, method, list);
                result.add(endpointGroupInfo);
            }
        }
        return result;
    }

}
