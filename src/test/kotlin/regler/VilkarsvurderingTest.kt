package com.example.regler

import com.example.modell.*
import kotlin.test.*

class VilkarsvurderingTest {

    private val grunnbelop = 130160

    private fun lagInntekt(maneder: List<String>, belop: Int = 45000) = maneder.map {
        Inntekt(maned = it, type = Inntektstype.ARBEID, belop = belop)
    }

    private fun lagSoknad(
        erNorskBorger: Boolean = true,
        inntektshistorikk: List<Inntekt> = lagInntekt(
            listOf("2026-01", "2026-02", "2026-03", "2026-04", "2026-05",
                   "2026-06", "2026-07", "2026-08", "2026-09", "2026-10")
        ),
        termindato: String = "2026-11-15",
    ) = Soknad(
        id = "test",
        beskrivelse = "test",
        fnr = "12345678901",
        erNorskBorger = erNorskBorger,
        termindato = termindato,
        oppgittArsinntekt = 540000,
        inntektshistorikk = inntektshistorikk,
        antallBarn = 1,
        rettsforhold = "begge",
        dekningsgrad = 100,
    )

    @Test
    fun `norsk borger riktig inntekt passerer vilkarsvurdering`() {
        val resultat = vilkarsvurdering(lagSoknad(), grunnbelop)
        assertNull(resultat)
    }

    @Test
    fun `ikke norsk borger gir avslag`() {
        val resultat = vilkarsvurdering(lagSoknad(erNorskBorger = false), grunnbelop)
        assertIs<Vedtak.Avslag>(resultat)
    }

    @Test
    fun `for fa maneder med inntekt gir engangsstonad`() {
        val inntekt = lagInntekt(listOf("2026-01", "2026-02", "2026-03", "2026-04"))
        val resultat = vilkarsvurdering(lagSoknad(inntektshistorikk = inntekt), grunnbelop)
        assertIs<Vedtak.Engangsstonad>(resultat)
    }
}
