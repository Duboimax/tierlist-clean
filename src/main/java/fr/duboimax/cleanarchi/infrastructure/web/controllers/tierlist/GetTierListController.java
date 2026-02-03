package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.dtos.responses.GetTierListResponse;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.GetTierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tierlist")
public class GetTierListController {

    private final GetTierList getTierList;

    public GetTierListController(GetTierList getTierList) {
        this.getTierList = getTierList;
    }

    @GetMapping
    public ResponseEntity<GetTierListResponse> get(@AuthenticationPrincipal String userId) {
        UserId id = new UserId(UUID.fromString(userId));
        return ResponseEntity.ok(getTierList.execute(id));
    }
}
