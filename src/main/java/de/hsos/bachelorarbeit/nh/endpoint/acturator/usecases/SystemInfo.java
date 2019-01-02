package de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class SystemInfo {
    public static final int CPU_COUNT =  Runtime.getRuntime().availableProcessors();
    private static final OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    /**
     *
     * @return JVM CPU Load (%)
     */
    public static double getProcessCpuLoad(){
        return osBean.getProcessCpuLoad();
    }

    /**
     *
     * @return System CPU Load (%)
     */
    public static double getSystemCpuLoad(){
        return osBean.getSystemCpuLoad();
    }

}
