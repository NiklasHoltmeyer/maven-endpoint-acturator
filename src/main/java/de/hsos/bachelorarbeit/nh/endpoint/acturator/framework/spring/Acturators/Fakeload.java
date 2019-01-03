package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "fakeload")
public class Fakeload {
    @GetMapping("/80")
    public String l80(){
        int coreCount = Runtime.getRuntime().availableProcessors();
        int threadPerCoreCount = 10;
        double load = 0.8;
        int duration = 10000;

        this.fakeLoad(coreCount, threadPerCoreCount, load, duration);
        return "zeze";
    }

    @GetMapping("/90L")
    public void l90l(){
        int coreCount = Runtime.getRuntime().availableProcessors();
        int threadPerCoreCount = 10;
        double load = 0.9;
        int duration = 10000;

        while(true) this.fakeLoad(coreCount, threadPerCoreCount, load, duration);
    }


    @GetMapping("/50")
    public String l50(){
        int coreCount = Runtime.getRuntime().availableProcessors();
        int threadPerCoreCount = 10;
        double load = 0.5;
        int duration = 10000;

        this.fakeLoad(coreCount, threadPerCoreCount, load, duration);
        return "zeze";
    }

    private void fakeLoad(int coreCount, int threadPerCoreCount, double load, int duration) {
        for(int thread = 0; thread < coreCount * threadPerCoreCount; ++thread){
            new BusyThread("Thread" + thread, load, duration).start();
        }
    }

    @GetMapping("/info/")
    public String info(){
        return "/fakeload/{cpuCount}/{threadPerCoreCount}/{load}/{duration}/";
    }
}

class BusyThread extends Thread {
    //Quelle: https://caffinc.github.io/2016/03/cpu-load-generator/
    private double load;
    private long duration;

    /**
     * Constructor which creates the thread
     * @param name Name of this thread
     * @param load Load % that this thread should generate
     * @param duration Duration that this thread should generate the load for
     */
    public BusyThread(String name, double load, long duration) {
        super(name);
        this.load = load;
        this.duration = duration;
    }

    /**
     * Generates the load when run
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            // Loop for the given duration
            while (System.currentTimeMillis() - startTime < duration) {
                // Every 100ms, sleep for the percentage of unladen time
                if (System.currentTimeMillis() % 100 == 0) {
                    Thread.sleep((long) Math.floor((1 - load) * 100));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
