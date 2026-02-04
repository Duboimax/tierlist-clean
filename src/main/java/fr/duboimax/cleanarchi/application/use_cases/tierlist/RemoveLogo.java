package fr.duboimax.cleanarchi.application.use_cases.tierlist;

import fr.duboimax.cleanarchi.application.dtos.requests.RemoveLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.RemoveLogoResponse;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.domain.exception.LogoNotFoundException;
import fr.duboimax.cleanarchi.domain.exception.TierListNotFoundException;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.util.UUID;

public class RemoveLogo {

    private final TierListRepository tierListRepository;

    public RemoveLogo(TierListRepository tierListRepository) {
        this.tierListRepository = tierListRepository;
    }

    public RemoveLogoResponse execute(RemoveLogoRequest request, UserId userId) {
        TierList tierList = tierListRepository.findByUserId(userId)
                .orElseThrow(() -> new TierListNotFoundException(userId.value().toString()));

        LogoId logoId = new LogoId(UUID.fromString(request.logoId()));

        if (tierList.getLogoTier(logoId).isEmpty()) {
            throw new LogoNotFoundException(request.logoId());
        }

        tierList.removeLogo(logoId);
        tierListRepository.save(tierList);

        return new RemoveLogoResponse(request.logoId(), "Logo removed from tier list");
    }
}
