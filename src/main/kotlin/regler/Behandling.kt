package com.example.regler

import com.example.modell.*

fun behandleSoknad(soknad: Soknad, grunnbelop: Int): Vedtak {
    vilkarsvurdering(soknad, grunnbelop)?.let { return it }

    val beregningsgrunnlag = beregningsgrunnlag(soknad, grunnbelop) ?: return Vedtak.ManuellVurdering("Beregnet inntekt avviker mer enn 25% fra oppgitt inntekt")

    val stonadsperiodeUker = stonadsperiode(soknad)

    val kvoter = kvotefordeling(soknad, stonadsperiodeUker)

    return Vedtak.Innvilget(
        beregningsgrunnlag = beregningsgrunnlag,
        stonadsperiodeUker = stonadsperiodeUker,
        kvoter = kvoter,
    )
}
