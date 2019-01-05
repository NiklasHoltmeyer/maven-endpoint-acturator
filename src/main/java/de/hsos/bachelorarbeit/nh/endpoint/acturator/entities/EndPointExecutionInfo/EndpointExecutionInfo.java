package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EndpointExecutionInfo {
    String url;
    String method;
    Unit<Long> executionTime;
    Unit<Double> cpuUsage;
    Unit<Double> cpuUsageSystem;
    Unit<Double> memoryUsage;
    Unit<String> responseSize;

    public EndpointExecutionInfo(String url, String method, Unit<Long> executionTime, Unit<Double> cpuUsage, Unit<Double> cpuUsageSystem, Unit<Double> memoryUsage, Unit<String> responseSize) {
        this.url = url;
        this.method = method;
        this.executionTime = executionTime;
        this.cpuUsage = cpuUsage;
        this.cpuUsageSystem = cpuUsageSystem;
        this.memoryUsage = memoryUsage;
        this.responseSize = responseSize;
    }

    public Unit<Double> getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Unit<Double> memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Unit<Long> getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Unit<Long> executionTime) {
        this.executionTime = executionTime;
    }

    public Unit<Double> getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Unit<Double> cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Unit<Double> getCpuUsageSystem() {
        return cpuUsageSystem;
    }

    public void setCpuUsageSystem(Unit<Double> cpuUsageSystem) {
        this.cpuUsageSystem = cpuUsageSystem;
    }

    public Unit<String> getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(Unit<String> responseSize) {
        this.responseSize = responseSize;
    }

}


