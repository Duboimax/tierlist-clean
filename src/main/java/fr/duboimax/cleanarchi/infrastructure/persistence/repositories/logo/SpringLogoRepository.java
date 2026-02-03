package fr.duboimax.cleanarchi.infrastructure.persistence.repositories.logo;

import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;
import fr.duboimax.cleanarchi.infrastructure.persistence.entities.LogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringLogoRepository extends JpaRepository<LogoEntity, String> {
    boolean existsByLogoName(String name);
}
