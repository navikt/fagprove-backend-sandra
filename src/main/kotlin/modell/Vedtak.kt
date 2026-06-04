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
    data class Avslag(val begrunnelse: String) : Vedtak()

    @Serializable
    @SerialName("Engangsstonad")
    data class Engangsstonad(val begrunnelse: String, val belop: Int) : Vedtak()

    @Serializable
    @SerialName("ManuellVurdering")
    data class ManuellVurdering(val begrunnelse: String) : Vedtak()

    @Serializable
    @SerialName("Innvilget")
    data class Innvilget(
        val beregningsgrunnlag: Int,
        val stonadsperiodeUker: Int,
        val kvoter: Kvoter,
    ) : Vedtak()
}
