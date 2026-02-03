package fr.duboimax.cleanarchi.infrastructure.persistence.repositories.logo;

import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.infrastructure.persistence.mappers.LogoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaLogoRepository implements CompanyLogoRepository {

    private final SpringLogoRepository springLogoRepository;

    public JpaLogoRepository(SpringLogoRepository springLogoRepository) {
        this.springLogoRepository = springLogoRepository;
    }


    @Override
    public void save(CompanyLogo logo) {
        springLogoRepository.save(LogoMapper.toEntity(logo));
    }

    @Override
    public boolean existsByCompanyName(CompanyName name) {
        return springLogoRepository.existsByLogoName(name.value());
    }

    @Override
    public List<CompanyLogo> findAll() {
       return springLogoRepository.findAll()
               .stream().map(LogoMapper::toDomain).toList();
    }

    @Override
    public Optional<CompanyLogo> findById(LogoId id) {
        return springLogoRepository.findById(id.value().toString())
                .map(LogoMapper::toDomain);
    }
}
