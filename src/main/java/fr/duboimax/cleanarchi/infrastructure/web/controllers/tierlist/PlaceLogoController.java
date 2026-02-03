package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.PlaceLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.PlaceLogoResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.PlaceLogo;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tierlist")
public class PlaceLogoController {

    private final PlaceLogo placeLogo;

    public PlaceLogoController(PlaceLogo placeLogo) {
        this.placeLogo = placeLogo;
    }

    @PostMapping("/place")
    public ResponseEntity<PlaceLogoResponse> place(
            @RequestBody PlaceLogoRequest request,
            @AuthenticationPrincipal String userId
    ) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity.ok(placeLogo.execute(request, id));
    }
}