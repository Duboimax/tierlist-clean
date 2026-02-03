package fr.duboimax.cleanarchi.application.use_cases.tierlist;

import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import fr.duboimax.cleanarchi.application.dtos.responses.GetTierListResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.GetTierListResponse.PlacementResponse;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.domain.exception.TierListNotFoundException;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.Tier;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GetTierList {

    private final TierListRepository tierListRepository;
    private final CompanyLogoRepository companyLogoRepository;
    private final LogoUrlBuilder logoUrlBuilder;

    public GetTierList(TierListRepository tierListRepository, CompanyLogoRepository companyLogoRepository, LogoUrlBuilder logoUrlBuilder) {
        this.tierListRepository = tierListRepository;
        this.companyLogoRepository = companyLogoRepository;
        this.logoUrlBuilder = logoUrlBuilder;
    }

    public GetTierListResponse execute(UserId userId) {
        TierList tierList = tierListRepository.findByUserId(userId)
                .orElseThrow(() -> new TierListNotFoundException(userId.value().toString()));

        List<PlacementResponse> placements = new ArrayList<>();

        for (Map.Entry<LogoId, Tier> entry : tierList.getPlacements().entrySet()) {
            LogoId logoId = entry.getKey();
            Tier tier = entry.getValue();

            Optional<CompanyLogo> logo = companyLogoRepository.findById(logoId);

            if (logo.isPresent()) {
                placements.add(new PlacementResponse(
                        logoId.value().toString(),
                        logo.get().getCompanyName().value(),
                        logoUrlBuilder.buildUrl(logo.get().getLogoIdentifier()),
                        tier.name()
                ));
            }
        }

        return new GetTierListResponse(
                tierList.getId().value().toString(),
                placements
        );
    }
}
