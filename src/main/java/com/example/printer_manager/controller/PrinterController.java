package com.example.printer_manager.controller;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/printers")
public class PrinterController {
    @Autowired
    private PrinterService printerService;

    @GetMapping
    public String getAllPrinters(Model model) {
        model.addAttribute("printers", printerService.getAllPrinters());
        return "printers";
    }

    @GetMapping("/add")
    public String addPrinterForm(Model model) {
        model.addAttribute("printer", new Printer());
        return "addPrinter";
    }

    @PostMapping("/add")
    public String addPrinter(@ModelAttribute Printer printer) {
        printerService.savePrinter(printer);
        return "redirect:/printers";
    }

    @GetMapping("/delete/{id}")
    public String deletePrinter(@PathVariable Long id) {
        printerService.deletePrinter(id);
        return "redirect:/printers";
    }
}
