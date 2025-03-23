package com.paynet.tgp_challenge.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "country_name", nullable = false)
    private String countryName;
    
    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;
    
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<GdpGrowth> gdpGrowthList;
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<GdpGrowth> getGdpGrowthList() {
        return gdpGrowthList;
    }

    public void setGdpGrowthList(List<GdpGrowth> gdpGrowthList) {
        this.gdpGrowthList = gdpGrowthList;
    }
}
