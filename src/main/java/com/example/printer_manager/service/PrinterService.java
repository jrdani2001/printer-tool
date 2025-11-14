package com.example.printer_manager.service;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.repository.PrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Printer> findById(Long id) {
        return printerRepository.findById(id);
    }

    public Printer savePrinter(Printer printer) {
        return printerRepository.save(printer);
    }

    public void deletePrinter(Long id) {
        printerRepository.deleteById(id);
    }

    public Printer updatePrinter(Long id, Printer printerDetails) {
        Printer printer = printerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Printer not found with id: " + id));

        printer.setManufacturer(printerDetails.getManufacturer());
        printer.setType(printerDetails.getType());
        printer.setSn(printerDetails.getSn());
        printer.setIp(printerDetails.getIp());
        printer.setLocation(printerDetails.getLocation());

        return printerRepository.save(printer);
    }

}
