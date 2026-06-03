package com.example.regler

import com.example.modell.*

fun behandleSoknad(soknad: Soknad, grunnbelop: Int): Vedtak {
    vilkarsvurdering(soknad, grunnbelop)?.let { return it }

    val beregningsgrunnlag = beregningsgrunnlag(soknad, grunnbelop) ?: return Vedtak.ManuellVurdering

    val stonadsperiodeUker = stonadsperiode(soknad)

    // TODO: regel 5 – kvotefordeling

    return Vedtak.Innvilget(
        beregningsgrunnlag = beregningsgrunnlag,
        stonadsperiodeUker = stonadsperiodeUker,
        kvoter = Kvoter(0, 0, 0, 0, 0),
    )
}
