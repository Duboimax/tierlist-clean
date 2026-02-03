package fr.duboimax.cleanarchi.infrastructure.persistence.mappers;

import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.Tier;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierListId;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import fr.duboimax.cleanarchi.infrastructure.persistence.entities.TierListEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TierListMapper {

    public static TierListEntity toEntity(TierList tierList) {
        Map<String, String> placements = new HashMap<>();
        tierList.getPlacements().forEach((logoId, tier) ->
                placements.put(logoId.value().toString(), tier.name())
        );

        return new TierListEntity(
                tierList.getId().value().toString(),
                tierList.getUserId().value().toString(),
                placements,
                tierList.getCreatedAt(),
                tierList.getUpdatedAt()
        );
    }

    public static TierList toDomain(TierListEntity entity) {
        Map<LogoId, Tier> placements = new HashMap<>();
        entity.getPlacements().forEach((logoId, tier) ->
                placements.put(new LogoId(UUID.fromString(logoId)), Tier.valueOf(tier))
        );

        return new TierList(
                new TierListId(UUID.fromString(entity.getId())),
                new UserId(UUID.fromString(entity.getUserId())),
                placements,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
