package com.example.regler

import com.example.modell.*
import kotlin.test.*

class BeregningsgrunnlagTest {

    private val grunnbelop = 130160

    private fun lagSoknad(
        oppgittArsinntekt: Int = 540000,
        inntektshistorikk: List<Inntekt> = listOf(
            Inntekt(maned = "2026-08", type = Inntektstype.ARBEID, belop = 45000),
            Inntekt(maned = "2026-09", type = Inntektstype.ARBEID, belop = 45000),
            Inntekt(maned = "2026-10", type = Inntektstype.ARBEID, belop = 45000),
        ),
        termindato: String = "2026-11-15",
    ) = Soknad(
        id = "test",
        beskrivelse = "test",
        fnr = "12345678901",
        erNorskBorger = true,
        termindato = termindato,
        oppgittArsinntekt = oppgittArsinntekt,
        inntektshistorikk = inntektshistorikk,
        antallBarn = 1,
        rettsforhold = "begge",
        dekningsgrad = 100,
    )

    @Test
    fun `beregner grunnlag fra siste 3 maneder`() {
        val resultat = beregningsgrunnlag(lagSoknad(), grunnbelop)
        assertEquals(540000, resultat)
    }

    @Test
    fun `for stort avvik gir null`() {
        val resultat = beregningsgrunnlag(lagSoknad(oppgittArsinntekt = 300000), grunnbelop)
        assertNull(resultat)
    }

    @Test
    fun `kapper ved 6G`() {
        val hoyInntekt = listOf(
            Inntekt(maned = "2026-08", type = Inntektstype.ARBEID, belop = 100000),
            Inntekt(maned = "2026-09", type = Inntektstype.ARBEID, belop = 100000),
            Inntekt(maned = "2026-10", type = Inntektstype.ARBEID, belop = 100000),
        )
        val resultat = beregningsgrunnlag(lagSoknad(oppgittArsinntekt = 1200000, inntektshistorikk = hoyInntekt), grunnbelop)
        assertEquals(grunnbelop * 6, resultat)
    }
}
