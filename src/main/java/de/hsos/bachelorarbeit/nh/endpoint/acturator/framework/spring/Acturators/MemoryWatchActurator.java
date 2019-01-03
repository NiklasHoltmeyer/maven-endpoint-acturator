package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResult;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.CPUWatch;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.MemoryWatch;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.Watch;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "memoryWatch")
public class MemoryWatchActurator {
    Watch watch;

    @GetMapping(value = "/start", produces = "application/json")
    public String start(){
        watch = new MemoryWatch(10);
        new Thread(watch).start();
        return "{\"status\":\"started\"}";
    }

    @GetMapping(value = "/stop", produces = "application/json")
    public ResponseEntity<WatchResult<Double>> stop(){
        if(watch != null){
            watch.stop();
            WatchResult<Double> result = watch.getResult();
            watch = null;
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(501).build();
    }
}
