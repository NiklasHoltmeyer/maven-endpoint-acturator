package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities;

public class WatchResult<T> {
    private boolean success = true;
    private String errorMessage="";

    private Unit<T> duration;
    private Unit<T> firstValue;
    private Unit<T> average;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Unit<T> getDuration() {
        return duration;
    }

    public void setDuration(Unit<T> duration) {
        this.duration = duration;
    }

    public Unit<T> getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Unit<T> firstValue) {
        this.firstValue = firstValue;
    }

    public Unit<T> getAverage() {
        return average;
    }

    public void setAverage(Unit<T> average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "WatchResult{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                ", duration=" + duration +
                ", firstValue=" + firstValue +
                ", average=" + average +
                '}';
    }
}

