package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResult;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.CPUWatch;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.Watch;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "cpuWatch")
public class CpuWatchActurator {
    Watch cPUWatch;

    @GetMapping(value = "/start", produces = "application/json")
    public String start(){
        cPUWatch = new CPUWatch(10);
        new Thread(cPUWatch).start();
        return "{\"status\":\"started\"}";
    }

    @GetMapping(value = "/stop", produces = "application/json")
    public ResponseEntity<WatchResult<Double>> stop(){
        if(cPUWatch != null){
            cPUWatch.stop();
            WatchResult<Double> result = cPUWatch.getResult();
            cPUWatch = null;
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(501).build();
    }
}
