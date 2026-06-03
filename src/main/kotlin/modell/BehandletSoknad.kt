package com.example.modell

import kotlinx.serialization.Serializable

@Serializable
data class BehandletSoknad(
    val soknad: Soknad,
    val vedtak: Vedtak,
)
