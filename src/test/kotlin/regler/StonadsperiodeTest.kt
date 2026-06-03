package com.example.regler

import com.example.modell.*
import kotlin.test.*

class StonadsperiodeTest {

    private fun lagSoknad(
        rettsforhold: String = "begge",
        antallBarn: Int = 1,
        dekningsgrad: Int = 100,
    ) = Soknad(
        id = "test",
        beskrivelse = "test",
        fnr = "12345678901",
        erNorskBorger = true,
        termindato = "2026-11-15",
        oppgittArsinntekt = 540000,
        inntektshistorikk = emptyList(),
        antallBarn = antallBarn,
        rettsforhold = rettsforhold,
        dekningsgrad = dekningsgrad,
    )

    @Test
    fun `begge 1 barn 100 prosent gir 49 uker`() {
        assertEquals(49, stonadsperiode(lagSoknad()))
    }

    @Test
    fun `begge 1 barn 80 prosent gir 61 uker`() {
        assertEquals(61, stonadsperiode(lagSoknad(dekningsgrad = 80)))
    }

    @Test
    fun `kun-far 1 barn 100 prosent gir 40 uker`() {
        assertEquals(40, stonadsperiode(lagSoknad(rettsforhold = "kun-far")))
    }

    @Test
    fun `begge 2 barn 100 prosent gir 66 uker`() {
        assertEquals(66, stonadsperiode(lagSoknad(antallBarn = 2)))
    }

    @Test
    fun `begge 3 pluss barn gir 95 uker`() {
        assertEquals(95, stonadsperiode(lagSoknad(antallBarn = 4)))
    }
}
