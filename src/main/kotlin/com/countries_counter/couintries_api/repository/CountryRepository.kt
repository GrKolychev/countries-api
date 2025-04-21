package com.countries_counter.couintries_api.repository

import com.countries_counter.couintries_api.model.CountryDto
import com.countriescounter.jooq.tables.Country.COUNTRY
import com.countriescounter.jooq.tables.records.CountryRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

/**
 * Repository for accessing and manipulating country data from the database.
 *
 * This repository uses JOOQ's [DSLContext] to interact with the `countries` table.
 *
 * @property dsl the JOOQ DSL context used for building and executing SQL queries
 */
@Repository
class CountryRepository(private val dsl: DSLContext) {

    fun findAll(): List<CountryDto> {
        return dsl.selectFrom(COUNTRY)
            .fetch()
            .map { it.toDto() }
    }

    private fun CountryRecord.toDto(): CountryDto {
        return CountryDto(
            cca2 = this.cca2,
            cca3 = this.cca3,
            nameCommon = this.nameCommon,
            nameOfficial = this.nameOfficial,
            capital = parseCapital(this.capital),
            region = this.region,
            subregion = this.subregion,
            lat = this.lat,
            lng = this.lng,
            flag = this.flag
        )
    }

    private fun parseCapital(capitalJson: String): List<String> {
        return capitalJson
            .removePrefix("[")
            .removeSuffix("]")
            .split(',')
            .map { it.trim() }
    }

}