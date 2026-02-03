package fr.duboimax.cleanarchi.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "tier_lists")
public class TierListEntity {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @ElementCollection
    @CollectionTable(name = "tier_list_placements", joinColumns = @JoinColumn(name = "tier_list_id"))
    @MapKeyColumn(name = "logo_id")
    @Column(name = "tier")
    private Map<String, String> placements = new HashMap<>();

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public TierListEntity() {}

    public TierListEntity(String id, String userId, Map<String, String> placements, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.placements = placements;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Map<String, String> getPlacements() { return placements; }
    public void setPlacements(Map<String, String> placements) { this.placements = placements; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
