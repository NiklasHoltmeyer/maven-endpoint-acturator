package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;

public final class EndpointExecutionInfoBuilder {
    String url;
    String method;
    Unit<Long> executionTime;
    Unit<String> responseSize;

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

    public EndpointExecutionInfoBuilder withExecutionTime(Unit<Long> executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public EndpointExecutionInfoBuilder withResponseSize(Unit<String> responseSize) {
        this.responseSize = responseSize;
        return this;
    }

    public EndpointExecutionInfo build() {
        return new EndpointExecutionInfo(url, method, executionTime, responseSize);
    }
}
