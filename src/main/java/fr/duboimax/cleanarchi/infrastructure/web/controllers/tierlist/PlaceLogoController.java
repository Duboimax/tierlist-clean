package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.PlaceLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.PlaceLogoResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.PlaceLogo;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TierList", description = "Gestion de la tier list")
@RestController
@RequestMapping("/api/tierlist")
public class PlaceLogoController {

    private final PlaceLogo placeLogo;

    public PlaceLogoController(PlaceLogo placeLogo) {
        this.placeLogo = placeLogo;
    }

    @Operation(summary = "Placer un logo dans un tier")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Logo placé"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Logo ou TierList non trouvé", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/place")
    public ResponseEntity<ApiResponse<PlaceLogoResponse>> place(
            @RequestBody PlaceLogoRequest request,
            @AuthenticationPrincipal String userId
    ) {
        UserId id = new UserId(UUID.fromString(userId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, placeLogo.execute(request, id)));
    }
}