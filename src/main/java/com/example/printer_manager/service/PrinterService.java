package com.example.printer_manager.service;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.model.PrinterData;
import com.example.printer_manager.repository.PrinterRepository;
import com.example.printer_manager.repository.PrinterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PrinterService {
    @Autowired
    private PrinterRepository printerRepository;

    @Autowired
    private PrinterDataRepository printerDataRepository;

    @Autowired
    private SNMPService snmpService;

    public List<Printer> getAllPrinters() {
        return printerRepository.findAll();
    }

    public Printer savePrinter(Printer printer) {
        return printerRepository.save(printer);
    }

    public Printer getPrinterById(Long id) {
        return printerRepository.findById(id).orElse(null);
    }

    public void deletePrinter(Long id) {
        printerRepository.deleteById(id);
    }

    public void fetchAndSavePrinterData() {
        List<Printer> printers = getAllPrinters();
        for (Printer printer : printers) {
            try {
                String data = snmpService.getAsString(printer.getIpAddress(), "1.3.6.1.2.1.1.1.0"); // példa OID
                PrinterData printerData = new PrinterData();
                printerData.setPrinter(printer);
                printerData.setData(data);
                printerDataRepository.save(printerData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}