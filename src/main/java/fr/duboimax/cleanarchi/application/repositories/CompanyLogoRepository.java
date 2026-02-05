package fr.duboimax.cleanarchi.application.repositories;

import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;

import java.util.List;
import java.util.Optional;

public interface CompanyLogoRepository {
    void save(CompanyLogo logo);
    boolean existsByCompanyName(CompanyName name);
    List<CompanyLogo> findAll();
    Optional<CompanyLogo> findById(LogoId id);
    long count();
}
