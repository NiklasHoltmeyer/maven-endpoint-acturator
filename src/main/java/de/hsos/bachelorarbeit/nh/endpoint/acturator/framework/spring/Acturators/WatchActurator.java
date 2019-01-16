package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Acturators;

import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResult;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.entities.WatchResultGroup;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.CPUWatch;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.MemoryWatch;
import de.hsos.bachelorarbeit.nh.endpoint.acturator.usecases.watch.Watch;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "watch")
public class WatchActurator {
    Watch cPUWatch;
    Watch memoryWatch;
    @GetMapping(value = "/start", produces = "application/json")
    public String start(){
        cPUWatch = new CPUWatch(10);
        memoryWatch = new MemoryWatch(10);
        new Thread(cPUWatch).start();
        new Thread(memoryWatch).start();
        return "{\"status\":\"started-hi-\"}";
    }

    @GetMapping(value = "/stop", produces = "application/json")
    public ResponseEntity<WatchResultGroup> stop(){
        if(cPUWatch==null&&memoryWatch==null) return ResponseEntity.status(501).build();

        WatchResult<Double>[] results = new WatchResult[2];
        Watch[] watches = new Watch[]{cPUWatch, memoryWatch};
        for(int i = 0; i < 2; ++i){
            Watch w = watches[i];
            if(w!=null){
                w.stop();
                results[i] = w.getResult();
                watches[i]=null;
            }
        }

        WatchResultGroup watchResultGroup = new WatchResultGroup(results[0],results[1]);
        return ResponseEntity.ok(watchResultGroup);
    }
}


