package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities;

public final class WatchResultBuilder<T>{
    private boolean success = true;
    private String errorMessage="";
    private Unit<T> duration;
    private Unit<T> firstValue;
    private Unit<T> average;

    private WatchResultBuilder() {
    }

    public static WatchResultBuilder aWatchResult() {
        return new WatchResultBuilder();
    }

    public WatchResultBuilder withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public WatchResultBuilder withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public WatchResultBuilder withDuration(Unit<T> duration) {
        this.duration = duration;
        return this;
    }

    public WatchResultBuilder withFirstValue(Unit<T> firstValue) {
        this.firstValue = firstValue;
        return this;
    }

    public WatchResultBuilder withAverage(Unit<T> average) {
        this.average = average;
        return this;
    }

    public WatchResult build() {
        WatchResult watchResult = new WatchResult();
        watchResult.setSuccess(success);
        watchResult.setErrorMessage(errorMessage);
        watchResult.setDuration(duration);
        watchResult.setFirstValue(firstValue);
        watchResult.setAverage(average);
        return watchResult;
    }
}
