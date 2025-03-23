package com.paynet.tgp_challenge.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gdp_growth", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"country_id", "year"})
})
public class GdpGrowth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(name = "growth_percentage", precision = 10, scale = 6)
    private BigDecimal growthPercentage;
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getGrowthPercentage() {
        return growthPercentage;
    }

    public void setGrowthPercentage(BigDecimal growthPercentage) {
        this.growthPercentage = growthPercentage;
    }
}

