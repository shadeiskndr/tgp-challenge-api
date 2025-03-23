package com.paynet.tgp_challenge.service.impl;

import com.paynet.tgp_challenge.model.Country;
import com.paynet.tgp_challenge.model.GdpGrowth;
import com.paynet.tgp_challenge.repository.CountryRepository;
import com.paynet.tgp_challenge.repository.GdpGrowthRepository;
import com.paynet.tgp_challenge.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CsvImportServiceImpl implements CsvImportService {

    private final CountryRepository countryRepository;
    private final GdpGrowthRepository gdpGrowthRepository;

    @Autowired
    public CsvImportServiceImpl(CountryRepository countryRepository, GdpGrowthRepository gdpGrowthRepository) {
        this.countryRepository = countryRepository;
        this.gdpGrowthRepository = gdpGrowthRepository;
    }

    @Override
    @Transactional
    public void importGdpDataFromCsv() {
        try {
            ClassPathResource resource = new ClassPathResource("static/gdp-growth.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            
            // Read header
            String line = reader.readLine();
            String[] headers = line.split(",");
            
            // Process data rows
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                
                if (values.length >= 4) {
                    String countryName = values[0];
                    String countryCode = values[1];
                    
                    // Find or create country
                    Country country;
                    Optional<Country> existingCountry = countryRepository.findByCountryCode(countryCode);
                    
                    if (existingCountry.isPresent()) {
                        country = existingCountry.get();
                    } else {
                        country = new Country();
                        country.setCountryName(countryName);
                        country.setCountryCode(countryCode);
                        country = countryRepository.save(country);
                    }
                    
                    // Process GDP data for each year
                    for (int i = 3; i < values.length && i < headers.length; i++) {
                        String yearStr = headers[i];
                        String growthStr = values[i];
                        
                        if (!yearStr.isEmpty() && !growthStr.isEmpty()) {
                            try {
                                Integer year = Integer.parseInt(yearStr);
                                BigDecimal growth = new BigDecimal(growthStr);
                                
                                GdpGrowth gdpGrowth = new GdpGrowth();
                                gdpGrowth.setCountry(country);
                                gdpGrowth.setYear(year);
                                gdpGrowth.setGrowthPercentage(growth);
                                
                                gdpGrowthRepository.save(gdpGrowth);
                            } catch (NumberFormatException e) {
                                // Skip invalid data
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to import GDP data", e);
        }
    }
}

