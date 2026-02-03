package fr.duboimax.cleanarchi.domain.model.logo;

import fr.duboimax.cleanarchi.domain.exception.InvalidCompanyNameException;

import java.util.Objects;

public record CompanyName(String value) {

    private static final int MAX_LENGTH = 100;

    public CompanyName {
        Objects.requireNonNull(value, "Company name cannot be null");
        if (value.isBlank()) {
            throw new InvalidCompanyNameException(value);
        }
        if (value.length() > MAX_LENGTH) {
            throw new InvalidCompanyNameException(value);
        }
        value = value.trim();
    }
}
