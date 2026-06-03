package com.example

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class SoknadTest {

    @Test
    fun `henter soknader og returnerer liste`() = testApplication {
        configure()
        val client = createClient {
            install(ContentNegotiation) { json() }
        }
        val response = client.get("/api/soknader")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(ContentType.Application.Json, response.contentType()?.withoutParameters())

        val soknader = response.body<List<Soknad>>()
        assertTrue(soknader.isNotEmpty(), "Burde returnere minst én søknad")
    }

    @Test
    fun `soknader har forventede felter`() = testApplication {
        configure()
        val client = createClient {
            install(ContentNegotiation) { json() }
        }
        val soknader = client.get("/api/soknader").body<List<Soknad>>()
        val soknad = soknader.first()

        assertTrue(soknad.id.isNotBlank())
        assertTrue(soknad.fnr.length == 11)
        assertTrue(soknad.inntektshistorikk.isNotEmpty())
        assertTrue(soknad.dekningsgrad == 100 || soknad.dekningsgrad == 80)
        assertTrue(soknad.rettsforhold in listOf("begge", "kun-mor", "kun-far"))
    }
}
