package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.RemoveLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.RemoveLogoResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.RemoveLogo;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tierlist")
public class RemoveLogoController {

    private final RemoveLogo removeLogo;

    public RemoveLogoController(RemoveLogo removeLogo) {
        this.removeLogo = removeLogo;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse<RemoveLogoResponse>> remove(
            @RequestBody RemoveLogoRequest request,
            @AuthenticationPrincipal String userId
    ) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, removeLogo.execute(request, id)));
    }
}
