package fr.duboimax.cleanarchi.infrastructure.persistence.mappers;

import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.infrastructure.persistence.entities.LogoEntity;

public class LogoMapper {

    public static LogoEntity toEntity(CompanyLogo logo) {
        return new LogoEntity(
                logo.getId().value().toString(),
                logo.getCompanyName().value(),
                logo.getLogoIdentifier(),
                logo.getCreatedAt()
        );
    }

    public static CompanyLogo toDomain(LogoEntity entity) {
        return new CompanyLogo(
                new LogoId(java.util.UUID.fromString(entity.getId())),
                new CompanyName(entity.getLogoName()),
                entity.getLogoUrl(),
                entity.getCreatedAt()
        );
    }
}
