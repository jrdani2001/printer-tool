package com.example.printer_manager.repository;

import com.example.printer_manager.model.PrinterData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterDataRepository extends JpaRepository<PrinterData, Long> {
}