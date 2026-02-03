package fr.duboimax.cleanarchi.domain.model.user;

import java.util.Objects;

public record Password(String hashedValue) {

    public Password {
        Objects.requireNonNull(hashedValue, "Password cannot be null");
        if (hashedValue.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be blank");
        }
    }
}
