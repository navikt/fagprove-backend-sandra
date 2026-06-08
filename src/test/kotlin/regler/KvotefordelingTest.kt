package com.example.regler

import com.example.modell.*
import kotlin.test.*

class KvotefordelingTest {

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
    fun `begge 1 barn 100 prosent fordeler 49 uker riktig`() {
        val kvoter = kvotefordeling(lagSoknad(), 49)
        assertEquals(15, kvoter.modrekvote)
        assertEquals(15, kvoter.fedrekvote)
        assertEquals(16, kvoter.fellesperiode)
        assertEquals(3, kvoter.forhandskvote)
        assertEquals(0, kvoter.flerbarnsbonus)
    }

    @Test
    fun `begge 2 barn 100 prosent gir flerbarnsbonus`() {
        val kvoter = kvotefordeling(lagSoknad(antallBarn = 2), 66)
        assertEquals(17, kvoter.flerbarnsbonus)
        assertEquals(16, kvoter.fellesperiode)
    }

    @Test
    fun `kun-mor far alle uker minus forhandskvote`() {
        val kvoter = kvotefordeling(lagSoknad(rettsforhold = "kun-mor"), 49)
        assertEquals(46, kvoter.modrekvote)
        assertEquals(0, kvoter.fedrekvote)
        assertEquals(3, kvoter.forhandskvote)
        assertEquals(0, kvoter.fellesperiode)
    }

    @Test
    fun `kun-far far alle uker`() {
        val kvoter = kvotefordeling(lagSoknad(rettsforhold = "kun-far"), 40)
        assertEquals(0, kvoter.modrekvote)
        assertEquals(40, kvoter.fedrekvote)
        assertEquals(0, kvoter.forhandskvote)
        assertEquals(0, kvoter.fellesperiode)
    }

    // Feilende test
    @Test
    fun `kun-far far alle uker 2 barn`() {
        val kvoter = kvotefordeling(lagSoknad(rettsforhold = "kun-far", antallBarn = 2), 40)
        assertEquals(0, kvoter.modrekvote)
        assertEquals(40, kvoter.fedrekvote)
        assertEquals(0, kvoter.forhandskvote)
        assertEquals(0, kvoter.fellesperiode)
        assertEquals(17, kvoter.flerbarnsbonus)
    }
}
