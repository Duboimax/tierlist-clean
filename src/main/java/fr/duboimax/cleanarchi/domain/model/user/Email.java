package fr.duboimax.cleanarchi.domain.model.user;

import fr.duboimax.cleanarchi.domain.exception.InvalidEmailFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    public Email {
        Objects.requireNonNull(value, "Email cannot be null");
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailFormatException(value);
        }
        value = value.toLowerCase().trim();
    }
}
