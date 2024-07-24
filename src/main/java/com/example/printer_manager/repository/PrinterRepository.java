package com.example.printer_manager.repository;


import com.example.printer_manager.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrinterRepository extends JpaRepository<Printer, Long> {
}