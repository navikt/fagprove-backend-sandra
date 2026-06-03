package com.example.modell

import kotlinx.serialization.SerialName
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
    @SerialName("Avslag")
    data object Avslag : Vedtak()

    @Serializable
    @SerialName("Engangsstonad")
    data object Engangsstonad : Vedtak()

    @Serializable
    @SerialName("ManuellVurdering")
    data object ManuellVurdering : Vedtak()

    @Serializable
    @SerialName("Innvilget")
    data class Innvilget(
        val beregningsgrunnlag: Int,
        val stonadsperiodeUker: Int,
        val kvoter: Kvoter,
    ) : Vedtak()
}
