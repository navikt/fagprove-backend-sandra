package com.example.regler

import com.example.modell.Soknad
import com.example.modell.Vedtak

import java.time.YearMonth

fun vilkarsvurdering(soknad: Soknad, grunnbelop: Int): Vedtak? {
    if (!soknad.erNorskBorger) {
        return Vedtak.Avslag
    }

    val termindato = YearMonth.from(java.time.LocalDate.parse(soknad.termindato))
    val tiSisteMnd = (1..10).map { termindato.minusMonths(it.toLong()) }.toSet()

    val godkjentInntekt = soknad.inntektshistorikk
        .filter { it.type.erGodkjent() }
        .filter { YearMonth.parse(it.maned) in tiSisteMnd }

    val antallMndMedInntekt = godkjentInntekt.map { it.maned }.distinct().size

    if (antallMndMedInntekt < 6) {
        return Vedtak.Engangsstonad
    }

    val annualisert = godkjentInntekt.sumOf { it.belop } * 12 / 10
    val halvG = grunnbelop / 2

    if (annualisert < halvG) {
        return Vedtak.Engangsstonad
    }

    return null
}
