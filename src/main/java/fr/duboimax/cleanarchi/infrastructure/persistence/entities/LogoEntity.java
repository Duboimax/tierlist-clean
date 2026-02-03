package fr.duboimax.cleanarchi.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "logo")
public class LogoEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String logoName;

    @Column(nullable = false)
    private String logoUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


    public LogoEntity() {}

    public LogoEntity(String id, String logoName, String logoUrl, Instant createdAt) {
        this.id = id;
        this.logoName = logoName;
        this.logoUrl = logoUrl;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLogoName() { return logoName; }
    public void setLogoName(String logoName) { this.logoName = logoName; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
