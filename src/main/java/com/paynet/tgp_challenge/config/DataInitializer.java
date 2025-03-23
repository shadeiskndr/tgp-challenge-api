package com.paynet.tgp_challenge.config;

import com.paynet.tgp_challenge.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CsvImportService csvImportService;

    @Autowired
    public DataInitializer(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @Override
    public void run(String... args) {
        // Import GDP data from CSV on application startup
        csvImportService.importGdpDataFromCsv();
    }
}
