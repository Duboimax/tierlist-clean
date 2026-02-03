package fr.duboimax.cleanarchi.domain.model.logo;

import java.time.Instant;
import java.util.Objects;

public class CompanyLogo {

    final private LogoId id;

    final private CompanyName companyName;

    final private String logoIdentifier;

    final private Instant createdAt;


    public CompanyLogo(CompanyName companyName, String logoIdentifier) {
        this.id = LogoId.generate();
        this.companyName = Objects.requireNonNull(companyName);
        this.logoIdentifier = Objects.requireNonNull(logoIdentifier);
        this.createdAt = Instant.now();
    }

    public CompanyLogo(LogoId id, CompanyName companyName, String logoIdentifier,  Instant createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.logoIdentifier = logoIdentifier;
        this.createdAt = createdAt;
    }

    public LogoId getId() { return id; }
    public CompanyName getCompanyName() { return companyName; }
    public String getLogoIdentifier() { return logoIdentifier; }
    public Instant getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyLogo that = (CompanyLogo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
