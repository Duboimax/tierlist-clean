package fr.duboimax.cleanarchi.infrastructure.persistence.repositories.tierlist;

import fr.duboimax.cleanarchi.infrastructure.persistence.entities.TierListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringTierListRepository extends JpaRepository<TierListEntity, String> {
    Optional<TierListEntity> findByUserId(String userId);
}
