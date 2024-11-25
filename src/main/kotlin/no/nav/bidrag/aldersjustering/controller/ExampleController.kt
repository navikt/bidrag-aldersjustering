package no.nav.bidrag.aldersjustering.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import no.nav.bidrag.aldersjustering.model.HentPersonResponse
import no.nav.bidrag.aldersjustering.service.ExampleService
import no.nav.bidrag.domene.ident.Personident
import no.nav.security.token.support.core.api.Protected
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Protected
class ExampleController(
    val exampleService: ExampleService,
) {
    @PostMapping("/person")
    @Operation(
        description = "Henter person data",
        security = [SecurityRequirement(name = "bearer-key")],
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Hentet person data"),
            ApiResponse(responseCode = "404", description = "Fant ikke person"),
            ApiResponse(responseCode = "401", description = "Sikkerhetstoken er ikke gyldig"),
            ApiResponse(
                responseCode = "403",
                description = "Sikkerhetstoken er ikke gyldig, eller det er ikke gitt adgang til kode 6 og 7 (nav-ansatt)",
            ),
        ],
    )
    fun hentDialog(
        @RequestBody personident: Personident,
    ): HentPersonResponse = exampleService.hentDialogerForPerson(personident)
}
