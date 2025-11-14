package com.example.printer_manager.controller;

import com.example.printer_manager.model.Counter;
import com.example.printer_manager.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/counters")
@CrossOrigin(origins = "*")
public class CounterRestController {

    @Autowired
    private CounterService counterService;

    @GetMapping
    public ResponseEntity<List<Counter>> getAllCounters() {
        List<Counter> counters = counterService.getAllCounters();
        return ResponseEntity.ok(counters);
    }

    @GetMapping("/ip/{ip}")
    public ResponseEntity<List<Counter>> getCountersByIp(@PathVariable String ip) {
        List<Counter> counters = counterService.getCountersByIp(ip);
        return ResponseEntity.ok(counters);
    }

    @PostMapping
    public ResponseEntity<Counter> createCounter(@RequestBody Counter counter) {
        Counter savedCounter = counterService.saveCounter(counter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCounter);
    }
}
