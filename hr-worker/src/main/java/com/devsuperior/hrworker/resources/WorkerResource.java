package com.devsuperior.hrworker.resources;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/worker")
@Slf4j
@RefreshScope
public class WorkerResource {

//    @Value("${test.config}")
//    private String testConfig;

    @Autowired
    private Environment environment;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> workers = workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

//    @GetMapping("/configs")
//    public ResponseEntity<Void> getConfigs(){
//        log.info("CONFIG ="+testConfig);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findId(@PathVariable Long id){

//        try {
//            Thread.sleep(3000L);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        log.info("Port ="+environment.getProperty("local.server.port"));

        return ResponseEntity.ok(workerRepository.findById(id).get());
    }
}
