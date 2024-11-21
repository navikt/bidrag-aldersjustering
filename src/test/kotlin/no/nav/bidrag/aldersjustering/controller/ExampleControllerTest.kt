package no.nav.bidrag.aldersjustering.controller

import io.kotest.matchers.shouldBe
import no.nav.bidrag.aldersjustering.SpringTestRunner
import no.nav.bidrag.aldersjustering.model.HentPersonResponse
import no.nav.bidrag.domene.ident.Personident
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class ExampleControllerTest : SpringTestRunner() {
    @Test
    fun `Skal hente persondata`() {
        val httpEntity =
            HttpEntity(
                Personident("22496818540"),
            )
        stubUtils.stubBidragPersonResponse(HentPersonResponse("22496818540", "Navn Navnesen", "213213213"))

        val response =
            httpHeaderTestRestTemplate.exchange(
                "${rootUri()}/person",
                HttpMethod.POST,
                httpEntity,
                HentPersonResponse::class.java,
            )

        response.statusCode shouldBe HttpStatus.OK
    }
}
