package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ExportTierListResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.ExportTierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tierlist")
public class ExportTierListController {

    private final ExportTierList exportTierList;

    public ExportTierListController(ExportTierList exportTierList) {
        this.exportTierList = exportTierList;
    }

    @PostMapping("/export")
    public ResponseEntity<ApiResponse<ExportTierListResponse>> export(@AuthenticationPrincipal String userId) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, exportTierList.execute(id)));
    }
}
