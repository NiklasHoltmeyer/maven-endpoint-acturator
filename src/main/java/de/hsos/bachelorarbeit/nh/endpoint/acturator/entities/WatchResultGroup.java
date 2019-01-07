package de.hsos.bachelorarbeit.nh.endpoint.acturator.entities;

public class WatchResultGroup {
    WatchResult<Double> cpuWatchResult;
    WatchResult<Double> memoryWatchResult;

    public WatchResultGroup(WatchResult<Double> cpuWatchResult, WatchResult<Double> memoryWatchResult) {
        this.cpuWatchResult = cpuWatchResult;
        this.memoryWatchResult = memoryWatchResult;
    }

    public WatchResult<Double> getCpuWatchResult() {
        return cpuWatchResult;
    }

    public void setCpuWatchResult(WatchResult<Double> cpuWatchResult) {
        this.cpuWatchResult = cpuWatchResult;
    }

    public WatchResult<Double> getMemoryWatchResult() {
        return memoryWatchResult;
    }

    public void setMemoryWatchResult(WatchResult<Double> memoryWatchResult) {
        this.memoryWatchResult = memoryWatchResult;
    }
}
