package com.example

import com.example.klient.hentGrunnbelop
import com.example.klient.hentSoknader
import com.example.modell.BehandletSoknad
import com.example.regler.behandleSoknad
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/api/soknader") {
            try {
                val soknader = hentSoknader()
                val grunnbelop = hentGrunnbelop()

                val behandledeSoknader = soknader.map { soknad ->
                    BehandletSoknad(
                        soknad = soknad,
                        vedtak = behandleSoknad(soknad, grunnbelop),
                    )
                }

                call.respond(behandledeSoknader)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.ServiceUnavailable, "Kunne ikke hente data fra eksterne tjenester")
            }
        }
    }
}