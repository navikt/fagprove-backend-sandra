package com.example.regler

import com.example.modell.Kvoter
import com.example.modell.Soknad

fun kvotefordeling(soknad: Soknad, totalUker: Int): Kvoter {
    val forhandskvote = 3

    val barn = if (soknad.antallBarn >= 3) 3 else soknad.antallBarn
    val flerbarnsbonus = when (barn) {
        1 -> 0
        2 -> if (soknad.dekningsgrad == 100) 17 else 21
        else -> if (soknad.dekningsgrad == 100) 46 else 57
    }

    if (soknad.rettsforhold != "begge") {
        return Kvoter(
            modrekvote = if (soknad.rettsforhold == "kun-mor") totalUker - forhandskvote else 0,
            fedrekvote = if (soknad.rettsforhold == "kun-far") totalUker else 0,
            fellesperiode = 0,
            forhandskvote = if (soknad.rettsforhold == "kun-mor") forhandskvote else 0,
            flerbarnsbonus = flerbarnsbonus,
        )
    }

    val modrekvote = if (soknad.dekningsgrad == 100) 15 else 19
    val fedrekvote = if (soknad.dekningsgrad == 100) 15 else 19

    val fellesperiode = totalUker - modrekvote - fedrekvote - forhandskvote - flerbarnsbonus

    return Kvoter(
        modrekvote = modrekvote,
        fedrekvote = fedrekvote,
        fellesperiode = fellesperiode,
        forhandskvote = forhandskvote,
        flerbarnsbonus = flerbarnsbonus,
    )
}
