package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo.EndpointExecutionInfo;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EndpointGroupInfo {
    String url;
    String method;
    EndpointExecutionInfo endpointExecutionInfoAveraged;

    public EndpointGroupInfo(String url, String method, EndpointExecutionInfo endpointExecutionInfoAveraged) {
        this.url = url;
        this.method = method;
        this.endpointExecutionInfoAveraged = endpointExecutionInfoAveraged;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public EndpointExecutionInfo getEndpointExecutionInfoAveraged() {
        return endpointExecutionInfoAveraged;
    }

    public void setEndpointExecutionInfoAveraged(EndpointExecutionInfo endpointExecutionInfoAveraged) {
        this.endpointExecutionInfoAveraged = endpointExecutionInfoAveraged;
    }
}
