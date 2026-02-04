package fr.duboimax.cleanarchi.infrastructure.web.controllers.logo;

import fr.duboimax.cleanarchi.application.dtos.requests.AddCompanyLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.AddCompanyLogoResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.use_cases.logo.AddCompanyLogo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Logos", description = "Gestion des logos")
@RestController
@RequestMapping("/api")
public class AddLogoController {

    private final AddCompanyLogo addCompanyLogo;

    public AddLogoController(AddCompanyLogo addCompanyLogo) {
        this.addCompanyLogo = addCompanyLogo;
    }

    @Operation(summary = "Ajouter un logo")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Logo créé"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Logo déjà existant", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/logos")
    public ResponseEntity<ApiResponse<AddCompanyLogoResponse>> addLogo(@RequestBody AddCompanyLogoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, addCompanyLogo.execute(request)));
    }
}
