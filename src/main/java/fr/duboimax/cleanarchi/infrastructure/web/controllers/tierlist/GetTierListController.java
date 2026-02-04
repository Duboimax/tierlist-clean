package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.GetTierListResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.GetTierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Tag(name = "TierList", description = "Gestion de la tier list")
@RestController
@RequestMapping("/api/tierlist")
public class GetTierListController {

    private final GetTierList getTierList;

    public GetTierListController(GetTierList getTierList) {
        this.getTierList = getTierList;
    }

    @Operation(summary = "Récupérer sa tier list")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tier list récupérée"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "TierList non trouvée", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<ApiResponse<GetTierListResponse>> get(@AuthenticationPrincipal String userId) {
        UserId id = new UserId(UUID.fromString(userId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, getTierList.execute(id)));
    }
}
