package fr.duboimax.cleanarchi.domain.model.logo;

import java.util.Objects;
import java.util.UUID;

public record LogoId(UUID value) {

    public LogoId {
        Objects.requireNonNull(value, "LogoId cannot be null");
    }

    public static LogoId generate() {
        return new LogoId(UUID.randomUUID());
    }

    public static LogoId from(String value) {
        return new LogoId(UUID.fromString(value));
    }
}
