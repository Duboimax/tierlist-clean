package fr.duboimax.cleanarchi.domain.model.tierlist;

import java.util.Objects;
import java.util.UUID;

public record TierListId(UUID value) {

    public TierListId {
        Objects.requireNonNull(value, "TierListId cannot be null");
    }

    public static TierListId generate() {
        return new TierListId(UUID.randomUUID());
    }

    public static TierListId from(String value) {
        return new TierListId(UUID.fromString(value));
    }
}
