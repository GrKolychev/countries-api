package com.countries_counter.couintries_api.model

/**
 * DTO class represents a country with its data.
 *
 * @property cca2 The two-letter country code (ISO 3166-1 alpha-2).
 * @property cca3 The three-letter country code (ISO 3166-1 alpha-3).
 * @property nameCommon The common name of the country (e.g., "United States").
 * @property nameOfficial The official name of the country (e.g., "United States of America").
 * @property capital The capital city or cities of the country (e.g., "Washington, D.C."). Can be null.
 * @property region The continent or region the country is located in (e.g., "Americas").
 * @property subregion The specific subregion the country belongs to (e.g., "Northern America").
 * @property lat The latitude of the country (e.g., 37.0902). Can be null.
 * @property lng The longitude of the country (e.g., -95.7129). Can be null.
 * @property flag A string representing the country's flag, such as an emoji (e.g., "🇺🇸"). Can be null.
 *
 * @constructor Creates a new [CountryDto] instance with the provided details.
 */
data class CountryDto(
    val cca2: String,
    val cca3: String,
    val nameCommon: String,
    val nameOfficial: String?,
    val capital: List<String>?,
    val region: String?,
    val subregion: String?,
    val lat: Double?,
    val lng: Double?,
    val flag: String?
)
