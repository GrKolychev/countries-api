package com.countries_counter.couintries_api.controller

import com.countries_counter.couintries_api.model.CountryDto
import com.countries_counter.couintries_api.service.CountryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller providing access to countries related api.
 *
 * @property countryService [CountryService]
 */
@RestController
@RequestMapping("/api/countries")
class CountryController(private val countryService: CountryService) {

    private val logger = KotlinLogging.logger {}

    @Operation(
        summary = "Get all countries",
        description = "Returns a list of all countries with basic information"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved countries list"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @GetMapping
    fun getAllCountries(): List<CountryDto> {
        logger.info { "Incoming request to GET /api/countries" }
        return countryService.findAll()
    }

}