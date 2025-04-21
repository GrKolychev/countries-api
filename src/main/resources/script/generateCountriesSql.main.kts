@file:DependsOn("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
@file:DependsOn("com.fasterxml.jackson.core:jackson-databind:2.18.3")
@file:DependsOn("com.fasterxml.jackson.core:jackson-core:2.18.3")

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

val jsonFile = File("src/main/resources/data/countries.json")
val jsonData = jsonFile.readText()

val mapper = jacksonObjectMapper()

val countries = mapper.readValue<List<Map<String, Any>>>(jsonData)

val sql = StringBuilder()
sql.appendLine("INSERT INTO countries (cca2, cca3, name_common, name_official, capital, region, subregion, lat, lng, flag) VALUES")

countries.forEachIndexed { index, country ->
    val cca2 = country["cca2"]
    val cca3 = country["cca3"]
    val nameCommon = (country["name"] as Map<*, *>)["common"]
    val nameOfficial = (country["name"] as Map<*, *>)["official"]
    val capital = country["capital"]
    val region = country["region"]
    val subregion = country["subregion"]
    val latLng = country["latlng"] as List<Double>
    val lat = latLng[0]
    val lng = latLng[1]
    val flag = country["flag"]

    sql.appendLine("('$cca2', '$cca3', '$nameCommon', '$nameOfficial', '$capital', '$region', '$subregion', $lat, $lng, '$flag')${if (index < countries.size - 1) "," else ";"}")
}

val sqlFile = File("src/main/resources/db/migration/V2__insert_countries_from_json.sql")
sqlFile.writeText(sql.toString())