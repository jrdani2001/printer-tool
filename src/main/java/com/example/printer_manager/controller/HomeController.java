package com.example.printer_manager.controller;

import com.example.printer_manager.model.Printer;
import com.example.printer_manager.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PrinterService printerService;

    @GetMapping("/")
    public String home(Model model) {
        List<Printer> printers = printerService.getAllPrinters();
        model.addAttribute("printers", printers);
        return "printers";  // Ez a 'printers.html' fájlhoz irányít
    }
}
