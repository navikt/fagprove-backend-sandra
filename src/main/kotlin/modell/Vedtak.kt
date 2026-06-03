package com.example.modell

import kotlinx.serialization.Serializable

@Serializable
data class Kvoter(
    val modrekvote: Int,
    val fedrekvote: Int,
    val fellesperiode: Int,
    val forhandskvote: Int,
    val flerbarnsbonus: Int,
)

@Serializable
sealed class Vedtak {

    @Serializable
    data object Avslag : Vedtak()

    @Serializable
    data object Engangsstonad : Vedtak()

    @Serializable
    data object ManuellVurdering : Vedtak()

    @Serializable
    data class Innvilget(
        val beregningsgrunnlag: Int,
        val stonadsperiodeUker: Int,
        val kvoter: Kvoter,
    ) : Vedtak()
}
