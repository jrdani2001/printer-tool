package com.example.printer_manager.config;

import com.example.printer_manager.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    private PrinterService printerService;

    @Scheduled(cron = "0 0 * * * ?")
    public void fetchPrinterData() {
        printerService.fetchAndSavePrinterData();
    }
}
