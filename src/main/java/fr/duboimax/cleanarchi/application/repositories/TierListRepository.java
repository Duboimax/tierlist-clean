package fr.duboimax.cleanarchi.application.repositories;

import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.util.Optional;

public interface TierListRepository {
    void save(TierList tierList);
    Optional<TierList> findByUserId(UserId userId);
}
