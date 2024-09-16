package com.example.printer_manager.service;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.repository.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {

    @Autowired
    private PrinterRepository printerRepository;

    public List<Printer> getAllPrinters() {
        return printerRepository.findAll();
    }

    public Optional<Printer> findByIp(String ip) {
        return printerRepository.findByIp(ip);
    }

}
