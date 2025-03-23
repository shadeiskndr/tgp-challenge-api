package com.paynet.tgp_challenge.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class GdpResponseDTO {
    private String countryName;
    private String countryCode;
    private Map<Integer, BigDecimal> yearlyGrowth;
    
    // Getters and setters
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Map<Integer, BigDecimal> getYearlyGrowth() {
        return yearlyGrowth;
    }

    public void setYearlyGrowth(Map<Integer, BigDecimal> yearlyGrowth) {
        this.yearlyGrowth = yearlyGrowth;
    }
}
