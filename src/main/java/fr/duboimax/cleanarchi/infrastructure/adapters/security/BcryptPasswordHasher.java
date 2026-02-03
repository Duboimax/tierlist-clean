package fr.duboimax.cleanarchi.infrastructure.adapters.security;

import fr.duboimax.cleanarchi.application.contracts.auth.PasswordHasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String plainTextPassword) {
        return encoder.encode(plainTextPassword);
    }

    @Override
    public boolean verify(String plainTextPassword, String hashedPassword) {
        return encoder.matches(plainTextPassword, hashedPassword);
    }

    @Override
    public void fakeVerify() {
        encoder.matches("fake", "$2a$10$dummyhashvalue");
    }
}
