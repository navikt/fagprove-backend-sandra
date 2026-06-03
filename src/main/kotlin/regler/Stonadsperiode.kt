package com.example.regler

import com.example.modell.Soknad

fun stonadsperiode(soknad: Soknad): Int {
    val barn = if (soknad.antallBarn >= 3) 3 else soknad.antallBarn

    return when (soknad.rettsforhold) {
        "kun-far" -> when (barn) {
            1 -> if (soknad.dekningsgrad == 100) 40 else 52
            2 -> if (soknad.dekningsgrad == 100) 57 else 73
            else -> if (soknad.dekningsgrad == 100) 86 else 109
        }
        // begge og kun-mor
        else -> when (barn) {
            1 -> if (soknad.dekningsgrad == 100) 49 else 61
            2 -> if (soknad.dekningsgrad == 100) 66 else 82
            else -> if (soknad.dekningsgrad == 100) 95 else 118
        }
    }
}
