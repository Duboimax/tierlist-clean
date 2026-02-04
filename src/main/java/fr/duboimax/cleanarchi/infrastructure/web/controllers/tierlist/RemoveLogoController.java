package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.RemoveLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.RemoveLogoResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.RemoveLogo;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TierList", description = "Gestion de la tier list")
@RestController
@RequestMapping("/api/tierlist")
public class RemoveLogoController {

    private final RemoveLogo removeLogo;

    public RemoveLogoController(RemoveLogo removeLogo) {
        this.removeLogo = removeLogo;
    }

    @Operation(summary = "Retirer un logo de la tier list")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Logo retiré"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Logo non trouvé dans la tier list", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse<RemoveLogoResponse>> remove(
            @Valid @RequestBody RemoveLogoRequest request,
            @AuthenticationPrincipal String userId
    ) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, removeLogo.execute(request, id)));
    }
}
