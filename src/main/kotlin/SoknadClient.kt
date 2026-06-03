package com.example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val SOKNADER_URL = "https://api.digisis.org/api/foreldrepenger/soknader"

val httpClient = HttpClient(Java) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

suspend fun hentSoknader(): List<Soknad> {
    return httpClient.get(SOKNADER_URL).body()
}
