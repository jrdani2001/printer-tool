package com.example.printer_manager.controller;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/printers")
@CrossOrigin(origins = "*")
public class PrinterRestController {

    @Autowired
    private PrinterService printerService;

    @GetMapping
    public ResponseEntity<List<Printer>> getAllPrinters() {
        List<Printer> printers = printerService.getAllPrinters();
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Printer> getPrinterById(@PathVariable Long id) {
        Optional<Printer> printer = printerService.findById(id);
        return printer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Printer> createPrinter(@RequestBody Printer printer) {
        Printer savedPrinter = printerService.savePrinter(printer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrinter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Printer> updatePrinter(@PathVariable Long id, @RequestBody Printer printer) {
        try {
            Printer updatedPrinter = printerService.updatePrinter(id, printer);
            return ResponseEntity.ok(updatedPrinter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrinter(@PathVariable Long id) {
        try {
            printerService.deletePrinter(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
