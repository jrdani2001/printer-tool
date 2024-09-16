package com.example.printer_manager.service;

import com.example.printer_manager.model.Counter;
import com.example.printer_manager.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterService {

    @Autowired
    private CounterRepository counterRepository;

    public List<Counter> getAllCounters() {
        return counterRepository.findAll();
    }

    public void saveCounter(Counter counter) {
        counterRepository.save(counter);
    }
}
