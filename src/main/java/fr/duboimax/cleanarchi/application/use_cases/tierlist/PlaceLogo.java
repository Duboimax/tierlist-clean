package fr.duboimax.cleanarchi.application.use_cases.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.PlaceLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.PlaceLogoResponse;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.domain.exception.LogoNotFoundException;
import fr.duboimax.cleanarchi.domain.exception.TierListNotFoundException;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.Tier;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.util.Optional;

public class PlaceLogo {

    private final TierListRepository tierListRepository;
    private final CompanyLogoRepository companyLogoRepository;

    public PlaceLogo(TierListRepository tierListRepository, CompanyLogoRepository companyLogoRepository) {
        this.tierListRepository = tierListRepository;
        this.companyLogoRepository = companyLogoRepository;
    }

    public PlaceLogoResponse execute(PlaceLogoRequest request, UserId userId) {
        TierList tierList = tierListRepository.findByUserId(userId)
                .orElseThrow(() -> new TierListNotFoundException(userId.value().toString()));

        LogoId logoId = new LogoId(java.util.UUID.fromString(request.logoId()));
        if (companyLogoRepository.findById(logoId).isEmpty()) {
            throw new LogoNotFoundException(request.logoId());
        }

        Tier tier = Tier.valueOf(request.tier().toUpperCase());

        Optional<Tier> previousTier = tierList.getLogoTier(logoId);

        tierList.placeLogo(logoId, tier);

        tierListRepository.save(tierList);

        String message = previousTier.isPresent()
                ? "Logo moved from " + previousTier.get().name() + " to " + tier.name()
                : "Logo placed in " + tier.name();

        return new PlaceLogoResponse(request.logoId(), tier.name(), message);
    }
}
