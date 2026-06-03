package com.example.regler

import com.example.modell.*

fun behandleSoknad(soknad: Soknad, grunnbelop: Int): Vedtak {
    vilkarsvurdering(soknad, grunnbelop)?.let { return it }

    val beregningsgrunnlag = beregningsgrunnlag(soknad, grunnbelop) ?: return Vedtak.ManuellVurdering

    // TODO: regel 4 – stønadsperiode
    // TODO: regel 5 – kvotefordeling

    return Vedtak.Innvilget(
        beregningsgrunnlag = beregningsgrunnlag,
        stonadsperiodeUker = 0,
        kvoter = Kvoter(0, 0, 0, 0, 0),
    )
}
