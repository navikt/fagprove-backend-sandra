package com.example

import kotlinx.serialization.Serializable

@Serializable
enum class Inntektstype {
    ARBEID,
    SYKEPENGER,
    FORELDREPENGER,
    SVANGERSKAPSPENGER,
    DAGPENGER,
    AAP,
    PLEIEPENGER,
    STIPEND_LANEKASSEN;

    fun erGodkjent(): Boolean = this != STIPEND_LANEKASSEN
}

@Serializable
data class Inntekt(
    val maned: String,
    val type: Inntektstype,
    val belop: Int,
)

@Serializable
data class Soknad(
    val id: String,
    val beskrivelse: String,
    val fnr: String,
    val erNorskBorger: Boolean,
    val termindato: String,
    val oppgittArsinntekt: Int,
    val inntektshistorikk: List<Inntekt>,
    val antallBarn: Int,
    val rettsforhold: String,
    val dekningsgrad: Int,
)
