package fr.duboimax.cleanarchi.domain.model.tierlist;

import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.time.Instant;
import java.util.*;

public class TierList {

    final private TierListId id;
    final private UserId userId;
    final private Map<LogoId, Tier> placements;
    final private Instant createdAt;
    private Instant updatedAt;

    public TierList(UserId userId) {
        this.id = TierListId.generate();
        this.userId = Objects.requireNonNull(userId);
        this.placements = new HashMap<>();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public TierList(TierListId id, UserId userId, Map<LogoId, Tier> placements,  Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);
        this.placements = Objects.requireNonNull(placements);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public void placeLogo(LogoId logoId, Tier tier) {
        Objects.requireNonNull(logoId);
        Objects.requireNonNull(tier);
        placements.put(logoId, tier);
        this.updatedAt = Instant.now();
    }

    public void removeLogo(LogoId logoId) {
        placements.remove(logoId);
        this.updatedAt = Instant.now();
    }

    public Optional<Tier> getLogoTier(LogoId logoId) {
        return Optional.ofNullable(placements.get(logoId));
    }

    public Set<LogoId> getLogosInTier(Tier tier) {
        Set<LogoId> result = new HashSet<>();
        for (Map.Entry<LogoId, Tier> entry : placements.entrySet()) {
            if (entry.getValue() == tier) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public boolean isComplete(int totalLogos) {
        return placements.size() >= totalLogos;
    }

    public TierListId getId() { return id; }
    public UserId getUserId() { return userId; }
    public Map<LogoId, Tier> getPlacements() { return Collections.unmodifiableMap(placements); }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TierList tierList = (TierList) o;
        return Objects.equals(id, tierList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
