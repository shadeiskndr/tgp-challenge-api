package com.paynet.tgp_challenge.repository;

import com.paynet.tgp_challenge.model.GdpGrowth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdpGrowthRepository extends JpaRepository<GdpGrowth, Long> {
    List<GdpGrowth> findByCountryCountryCode(String countryCode);
    
    @Query("SELECT g FROM GdpGrowth g WHERE g.country.countryCode = :countryCode AND g.year BETWEEN :startYear AND :endYear ORDER BY g.year")
    List<GdpGrowth> findByCountryCodeAndYearRange(String countryCode, Integer startYear, Integer endYear);
}

