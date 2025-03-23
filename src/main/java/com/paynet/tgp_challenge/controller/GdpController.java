package com.paynet.tgp_challenge.controller;

import com.paynet.tgp_challenge.dto.GdpResponseDTO;
import com.paynet.tgp_challenge.service.CsvImportService;
import com.paynet.tgp_challenge.service.GdpGrowthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gdp")
@Tag(name = "GDP Growth", description = "GDP Growth data API")
@SecurityRequirement(name = "bearerAuth")
public class GdpController {

    private final GdpGrowthService gdpGrowthService;
    private final CsvImportService csvImportService;

    @Autowired
    public GdpController(GdpGrowthService gdpGrowthService, CsvImportService csvImportService) {
        this.gdpGrowthService = gdpGrowthService;
        this.csvImportService = csvImportService;
    }

    @Operation(summary = "Get all country codes", description = "Returns a list of all available country codes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved country codes")
    })
    @GetMapping("/countries")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<String>> getAllCountryCodes() {
        return ResponseEntity.ok(gdpGrowthService.getAllCountryCodes());
    }

    @Operation(summary = "Get GDP growth by country code", description = "Returns GDP growth data for a specific country")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved GDP data", 
                    content = @Content(schema = @Schema(implementation = GdpResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @GetMapping("/{countryCode}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<GdpResponseDTO> getGdpGrowthByCountryCode(
            @Parameter(description = "Country code, e.g., MYS for Malaysia") @PathVariable String countryCode) {
        GdpResponseDTO response = gdpGrowthService.getGdpGrowthByCountryCode(countryCode);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get GDP growth by country code and year range", 
              description = "Returns GDP growth data for a specific country within a year range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved GDP data", 
                    content = @Content(schema = @Schema(implementation = GdpResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @GetMapping("/{countryCode}/{startYear}/{endYear}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<GdpResponseDTO> getGdpGrowthByCountryCodeAndYearRange(
            @Parameter(description = "Country code, e.g., MYS for Malaysia") @PathVariable String countryCode,
            @Parameter(description = "Start year (inclusive)") @PathVariable Integer startYear,
            @Parameter(description = "End year (inclusive)") @PathVariable Integer endYear) {
        
        GdpResponseDTO response = gdpGrowthService.getGdpGrowthByCountryCodeAndYearRange(countryCode, startYear, endYear);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Import GDP data from CSV", description = "Imports GDP growth data from the CSV file")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully imported GDP data"),
        @ApiResponse(responseCode = "500", description = "Error importing data")
    })
    @PostMapping("/import")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> importGdpData() {
        csvImportService.importGdpDataFromCsv();
        return ResponseEntity.ok("GDP data imported successfully");
    }
}

