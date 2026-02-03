package fr.duboimax.cleanarchi.infrastructure.persistence.repositories.tierlist;

import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import fr.duboimax.cleanarchi.infrastructure.persistence.mappers.TierListMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaTierListRepository implements TierListRepository {

    private final SpringTierListRepository springTierListRepository;

    public JpaTierListRepository(SpringTierListRepository springTierListRepository) {
        this.springTierListRepository = springTierListRepository;
    }

    @Override
    public void save(TierList tierList) {
        springTierListRepository.save(TierListMapper.toEntity(tierList));
    }

    @Override
    public Optional<TierList> findByUserId(UserId userId) {
        return springTierListRepository.findByUserId(userId.value().toString())
                .map(TierListMapper::toDomain);
    }
}
