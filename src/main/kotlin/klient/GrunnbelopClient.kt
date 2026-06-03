package com.example.klient

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val GRUNNBELOP_URL = "https://g.nav.no/api/v1/grunnbeløp"

@Serializable
data class GrunnbelopResponse(
    @SerialName("grunnbeløp") val grunnbelop: Int,
)

suspend fun hentGrunnbelop(): Int {
    return httpClient.get(GRUNNBELOP_URL).body<GrunnbelopResponse>().grunnbelop
}
