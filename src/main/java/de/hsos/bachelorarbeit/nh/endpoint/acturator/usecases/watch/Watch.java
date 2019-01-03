package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.Unit;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResult;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResultBuilder;

import java.util.LinkedList;
import java.util.List;

public abstract class Watch<T> implements  Runnable {
    private boolean isRunning = true;
    private int sleepMilliSeconds;
    private Long startTime;

    protected WatchResult result;

    protected List<T> measurements;

    public Watch(int sleepMilliSeconds){
        this.sleepMilliSeconds=sleepMilliSeconds;
        this.init();
    }

    private void init(){
        this.measurements = new LinkedList<>();
        this.startTime = System.nanoTime();
    }

    @Override
    public void run(){
        while(isRunning){
            try {
                T measurement = this.measure();
                this.measurements.add(measurement);

                Thread.sleep(sleepMilliSeconds);
            } catch (InterruptedException e) {
                stop(e.toString());
            }
        }
    }

    public void stop(){
        stop(null);
    }

    private void stop(String errorMessage){
        isRunning = false;
        boolean success = errorMessage==null;

        Long endTime = System.nanoTime();
        Long d = (endTime - this.startTime);
        Unit<Double> duration = new Unit<Double>(((double)d/ 1000000000.0), "s");

        this.result = WatchResultBuilder.aWatchResult()
                .withDuration(duration)
                .withSuccess(success)
                .withErrorMessage(errorMessage)
                .build();

        int measurementCount = this.measurements.size();

        if(measurementCount > 0){
            Unit<T> firstValue = new Unit<>(this.measurements.get(0), "%");
            Unit<T> avgValue = new Unit<T>(this.calculateAverage(), "%");
            this.result.setFirstValue(firstValue);
            this.result.setAverage(avgValue);
        }
    }

    public WatchResult getResult(){
        if(isRunning) stop("getResult was called before the Watch was stopped!");
        return this.result;
    }

    protected abstract T measure();
    protected abstract T calculateAverage();
}
