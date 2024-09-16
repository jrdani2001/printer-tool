package com.example.printer_manager.repository;


import com.example.printer_manager.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {
    Optional<Printer> findByIp(String ip);
}
