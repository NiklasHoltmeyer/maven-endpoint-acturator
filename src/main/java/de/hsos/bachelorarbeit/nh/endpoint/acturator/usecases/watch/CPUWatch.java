package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.SystemInfo;

public class CPUWatch extends Watch<Double> {
    public CPUWatch(int sleepMilliSeconds){
        super(sleepMilliSeconds);
    }

    @Override
    protected Double measure() {
        return SystemInfo.getProcessCpuLoad();
    }

    @Override
    protected Double calculateAverage() {
        return this.measurements.stream()
                        .mapToDouble(Double::doubleValue)
                        .sum() / this.measurements.size();
    }
}
