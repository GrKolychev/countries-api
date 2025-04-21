package com.countries_counter.couintries_api.service

import com.countries_counter.couintries_api.model.CountryDto
import com.countries_counter.couintries_api.repository.CountryRepository
import org.springframework.stereotype.Service

/**
 * Service working with Countries.
 *
 * @property repository [CountryRepository] repo to access countries table in db
 */
@Service
class CountryService(private val repository: CountryRepository) {

    fun findAll(): List<CountryDto> {
        return repository.findAll()
    }

}