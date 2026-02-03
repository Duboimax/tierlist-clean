package fr.duboimax.cleanarchi.application.dtos.responses;

import java.util.List;

public record GetTierListResponse(
        String tierListId,
        List<PlacementResponse> placements
) {
    public record PlacementResponse(
            String logoId,
            String companyName,
            String logoUrl,
            String tier
    ) {}
}
