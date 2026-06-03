package com.example.regler

import com.example.modell.Soknad
import java.time.LocalDate
import java.time.YearMonth

fun beregningsgrunnlag(soknad: Soknad, grunnbelop: Int): Int? {
    val termindato = YearMonth.from(LocalDate.parse(soknad.termindato))
    val treSisteMnd = (1..3).map { termindato.minusMonths(it.toLong()) }.toSet()

    val inntektSiste3mnd = soknad.inntektshistorikk
        .filter { it.type.erGodkjent() }
        .filter { YearMonth.parse(it.maned) in treSisteMnd }
        .sumOf { it.belop }

    val snitt3mnd = inntektSiste3mnd / 3
    val arssats = snitt3mnd * 12

    if (soknad.oppgittArsinntekt > 0) {
        val avvik = (arssats - soknad.oppgittArsinntekt).toDouble() / soknad.oppgittArsinntekt
        if (avvik > 0.25) {
            return null // Manuell vurdering
        }
    }

    return minOf(arssats, grunnbelop * 6)
}
