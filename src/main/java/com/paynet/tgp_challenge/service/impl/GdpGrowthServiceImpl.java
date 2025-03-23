package com.paynet.tgp_challenge.service.impl;

import com.paynet.tgp_challenge.dto.GdpResponseDTO;
import com.paynet.tgp_challenge.model.Country;
import com.paynet.tgp_challenge.model.GdpGrowth;
import com.paynet.tgp_challenge.repository.CountryRepository;
import com.paynet.tgp_challenge.repository.GdpGrowthRepository;
import com.paynet.tgp_challenge.service.GdpGrowthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GdpGrowthServiceImpl implements GdpGrowthService {

    private final GdpGrowthRepository gdpGrowthRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public GdpGrowthServiceImpl(GdpGrowthRepository gdpGrowthRepository, CountryRepository countryRepository) {
        this.gdpGrowthRepository = gdpGrowthRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public GdpResponseDTO getGdpGrowthByCountryCode(String countryCode) {
        Optional<Country> countryOpt = countryRepository.findByCountryCode(countryCode);
        if (countryOpt.isEmpty()) {
            return null;
        }
        
        Country country = countryOpt.get();
        List<GdpGrowth> gdpGrowthList = gdpGrowthRepository.findByCountryCountryCode(countryCode);
        
        return createGdpResponseDTO(country, gdpGrowthList);
    }

    @Override
    public GdpResponseDTO getGdpGrowthByCountryCodeAndYearRange(String countryCode, Integer startYear, Integer endYear) {
        Optional<Country> countryOpt = countryRepository.findByCountryCode(countryCode);
        if (countryOpt.isEmpty()) {
            return null;
        }
        
        Country country = countryOpt.get();
        List<GdpGrowth> gdpGrowthList = gdpGrowthRepository.findByCountryCodeAndYearRange(countryCode, startYear, endYear);
        
        return createGdpResponseDTO(country, gdpGrowthList);
    }

    @Override
    public List<String> getAllCountryCodes() {
        return countryRepository.findAll().stream()
                .map(Country::getCountryCode)
                .collect(Collectors.toList());
    }
    
    private GdpResponseDTO createGdpResponseDTO(Country country, List<GdpGrowth> gdpGrowthList) {
        GdpResponseDTO responseDTO = new GdpResponseDTO();
        responseDTO.setCountryName(country.getCountryName());
        responseDTO.setCountryCode(country.getCountryCode());
        
        Map<Integer, BigDecimal> yearlyGrowth = new HashMap<>();
        for (GdpGrowth gdpGrowth : gdpGrowthList) {
            yearlyGrowth.put(gdpGrowth.getYear(), gdpGrowth.getGrowthPercentage());
        }
        
        responseDTO.setYearlyGrowth(yearlyGrowth);
        return responseDTO;
    }
}
