package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.SystemInfo;

public class MemoryWatch extends Watch<Double> {
    public MemoryWatch(int sleepMilliSeconds){
        super(sleepMilliSeconds);
    }

    @Override
    protected Double measure() {
        Runtime runtime = Runtime.getRuntime();
        long totalMem = runtime.totalMemory();
        long mem = totalMem - runtime.freeMemory();
        return (double)mem/totalMem;
    }

    @Override
    protected Double calculateAverage() {
        return this.measurements.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / this.measurements.size();
    }
}