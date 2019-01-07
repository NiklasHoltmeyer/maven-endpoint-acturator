package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.EndpointExecutionInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndpointGroupInfo;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                EndpointGroupInfo endpointGroupInfo =  this.averageValues(url, method, list);
                result.add(endpointGroupInfo);
            }
        }
        return result;
    }

    private EndpointGroupInfo averageValues(String url, String method, List<EndpointExecutionInfo> list){
        Double cpuUsage = list.stream().mapToDouble(x->x.getCpuUsage().getValue()).average().orElse(Double.NaN);
        Double cpuUsageSystem = list.stream().mapToDouble(x->x.getCpuUsageSystem().getValue()).average().orElse(Double.NaN);
        Double memoryUsageSystem = list.stream().mapToDouble(x->x.getMemoryUsage().getValue()).average().orElse(Double.NaN);
        Double responseSize = list.stream()
                .map(x->{
                    try{
                        return Double.valueOf(x.getResponseSize().getValue());
                    }catch(Exception e){
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average().orElse(Double.NaN);
        Double exeTime = list.stream().mapToDouble(x->new Double(x.getExecutionTime().getValue())).average().orElse(Double.NaN);

        String cpuUsageUnit = list.get(0).getCpuUsage().getUnit();
        String cpuUsageSystemUnit = list.get(0).getCpuUsageSystem().getUnit();
        String memoryUsageSystemUnit = list.get(0).getMemoryUsage().getUnit();
        String responseSizeUnit = list.get(0).getResponseSize().getUnit();
        String exeUnit = list.get(0).getExecutionTime().getUnit();

        if(cpuUsageUnit==null) cpuUsageUnit = "%";
        if(cpuUsageSystemUnit==null) cpuUsageSystemUnit = "%";
        if(memoryUsageSystemUnit==null) memoryUsageSystemUnit = "MB";
        if(responseSizeUnit==null) responseSizeUnit = "byte(s)";
        if(exeUnit==null) exeUnit = "ms";

        Unit<Double> _cpuUsageUnit = (cpuUsage.isNaN())? null : new Unit<Double>(cpuUsage, cpuUsageUnit);
        Unit<Double> _cpuUsageSystemUnit = (cpuUsageSystem.isNaN())? null : new Unit<Double>(cpuUsageSystem, cpuUsageSystemUnit);
        Unit<Double> _memoryUsageSystemUnit = (memoryUsageSystem.isNaN())? null : new Unit<Double>(memoryUsageSystem, memoryUsageSystemUnit);
        Unit<String> _responseSizeUnit = (responseSize.isNaN())? null : new Unit<String>(responseSize+"", responseSizeUnit);
        Unit<Long> _exeUnit = null;

        if((!exeTime.isNaN())){
            double d = exeTime;
            long l = (long) d;
            _exeUnit = new Unit<Long>(l, exeUnit);
        }

        EndpointExecutionInfo avg = new EndpointExecutionInfo("", "", _exeUnit,_cpuUsageUnit,_cpuUsageSystemUnit,_memoryUsageSystemUnit,_responseSizeUnit);
        return new EndpointGroupInfo(url, method, avg);
    }


}
