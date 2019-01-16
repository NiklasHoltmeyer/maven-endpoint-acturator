package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.EndPointExecutionInfo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EndpointExecutionInfo {
    String url;
    String method;
    @SerializedName(value="reactionTime", alternate={"executionTime"})
    Unit<Long> executionTime;

    Unit<String> responseSize;

    public EndpointExecutionInfo(String url, String method, Unit<Long> executionTime, Unit<String> responseSize) {
        this.url = url;
        this.method = method;
        this.executionTime = executionTime;
        this.responseSize = responseSize;
    }

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

    public Unit<String> getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(Unit<String> responseSize) {
        this.responseSize = responseSize;
    }

}


