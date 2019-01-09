package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities;

public class WatchResultGroup {
    WatchResult<Double> cpu;
    WatchResult<Double> memory;

    public WatchResultGroup(WatchResult<Double> cpuWatchResult, WatchResult<Double> memory) {
        this.cpu = cpuWatchResult;
        this.memory = memory;
    }

    public WatchResult<Double> getCpu() {
        return cpu;
    }

    public void setCpu(WatchResult<Double> cpu) {
        this.cpu = cpu;
    }

    public WatchResult<Double> getMemory() {
        return memory;
    }

    public void setMemory(WatchResult<Double> memory) {
        this.memory = memory;
    }
}
