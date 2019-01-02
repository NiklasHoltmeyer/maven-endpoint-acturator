package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo;

public final class EndpointExecutionInfoBuilder {
    String url;
    String method;
    ExecutionTime executionTime;
    Usage cpuUsage;
    Usage cpuUsageSystem;
    Usage memoryUsage;
    Size responseSize;

    private EndpointExecutionInfoBuilder() {
    }

    public static EndpointExecutionInfoBuilder anEndpointExecutionInfo() {
        return new EndpointExecutionInfoBuilder();
    }

    public EndpointExecutionInfoBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public EndpointExecutionInfoBuilder withMethod(String method) {
        this.method = method;
        return this;
    }

    public EndpointExecutionInfoBuilder withExecutionTime(ExecutionTime executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public EndpointExecutionInfoBuilder withCpuUsage(Usage cpuUsage) {
        this.cpuUsage = cpuUsage;
        return this;
    }

    public EndpointExecutionInfoBuilder withCpuUsageSystem(Usage cpuUsageSystem) {
        this.cpuUsageSystem = cpuUsageSystem;
        return this;
    }

    public EndpointExecutionInfoBuilder withMemoryUsage(Usage memoryUsage) {
        this.memoryUsage = memoryUsage;
        return this;
    }

    public EndpointExecutionInfoBuilder withResponseSize(Size responseSize) {
        this.responseSize = responseSize;
        return this;
    }

    public EndpointExecutionInfo build() {
        return new EndpointExecutionInfo(url, method, executionTime, cpuUsage, cpuUsageSystem, memoryUsage, responseSize);
    }
}
