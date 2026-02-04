package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ExportTierListResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.ExportTierList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Tag(name = "TierList", description = "Gestion de la tier list")
@RestController
@RequestMapping("/api/tierlist")
public class ExportTierListController {

    private final ExportTierList exportTierList;

    public ExportTierListController(ExportTierList exportTierList) {
        this.exportTierList = exportTierList;
    }

    @Operation(summary = "Exporter la tier list en PDF")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "PDF généré"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "TierList non trouvée", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/export")
    public ResponseEntity<ApiResponse<ExportTierListResponse>> export(@AuthenticationPrincipal String userId) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, exportTierList.execute(id)));
    }
}
