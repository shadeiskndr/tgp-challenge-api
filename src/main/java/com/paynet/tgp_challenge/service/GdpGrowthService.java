package com.paynet.tgp_challenge.service;

import com.paynet.tgp_challenge.dto.GdpResponseDTO;
import java.util.List;

public interface GdpGrowthService {
    GdpResponseDTO getGdpGrowthByCountryCode(String countryCode);
    GdpResponseDTO getGdpGrowthByCountryCodeAndYearRange(String countryCode, Integer startYear, Integer endYear);
    List<String> getAllCountryCodes();
}
