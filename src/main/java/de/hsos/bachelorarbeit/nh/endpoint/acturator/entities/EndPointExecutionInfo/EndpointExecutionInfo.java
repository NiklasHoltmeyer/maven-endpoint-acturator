package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EndpointExecutionInfo {
    String url;
    String method;
    ExecutionTime executionTime;
    Usage cpuUsage;
    Usage cpuUsageSystem;
    Usage memoryUsage;
    Size responseSize;

    public EndpointExecutionInfo(String url, String method, ExecutionTime executionTime, Usage cpuUsage, Usage cpuUsageSystem, Usage memoryUsage, Size responseSize) {
        this.url = url;
        this.method = method;
        this.executionTime = executionTime;
        this.cpuUsage = cpuUsage;
        this.cpuUsageSystem = cpuUsageSystem;
        this.memoryUsage = memoryUsage;
        this.responseSize = responseSize;
    }

    public Usage getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Usage memoryUsage) {
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

    public ExecutionTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(ExecutionTime executionTime) {
        this.executionTime = executionTime;
    }

    public Usage getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Usage cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Usage getCpuUsageSystem() {
        return cpuUsageSystem;
    }

    public void setCpuUsageSystem(Usage cpuUsageSystem) {
        this.cpuUsageSystem = cpuUsageSystem;
    }

    public Size getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(Size responseSize) {
        this.responseSize = responseSize;
    }

}


