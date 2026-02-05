package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.use_cases.tierlist.ExportAndDownloadTierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TierList", description = "Gestion de la tier list")
@RestController
@RequestMapping("/api/tierlist")
public class ExportTierListController {

    private final ExportAndDownloadTierList exportAndDownloadTierList;

    public ExportTierListController(ExportAndDownloadTierList exportAndDownloadTierList) {
        this.exportAndDownloadTierList = exportAndDownloadTierList;
    }

    @Operation(summary = "Exporter et télécharger la tier list en PDF")
    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@AuthenticationPrincipal String userId) {
        UserId id = new UserId(UUID.fromString(userId));

        ExportAndDownloadTierList.ExportResult result = exportAndDownloadTierList.execute(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.filename() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(result.content());
    }
}