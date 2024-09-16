package com.example.printer_manager.controller;

import com.example.printer_manager.model.Counter;
import com.example.printer_manager.model.Printer;
import com.example.printer_manager.service.CounterService;
import com.example.printer_manager.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @Autowired
    private CounterService counterService;

    @GetMapping("/printers")
    public String getPrinters(Model model) {
        List<Printer> printers = printerService.getAllPrinters();
        model.addAttribute("printers", printers);
        return "printers";  // Return the template named 'printers.html'
    }


    @PostMapping("/counters")
    public String saveCounter(@RequestParam String ip, @RequestParam Long counterValue) {
        Optional<Printer> printer = printerService.findByIp(ip);

        if (printer.isPresent()) {
            Counter counter = new Counter();
            counter.setType(printer.get().getType());
            counter.setIp(ip);
            counter.setLocation(printer.get().getLocation());
            counter.setSn(printer.get().getSn());
            counter.setCounter(counterValue);
            counterService.saveCounter(counter);
        }

        return "redirect:/counters";
    }
}
