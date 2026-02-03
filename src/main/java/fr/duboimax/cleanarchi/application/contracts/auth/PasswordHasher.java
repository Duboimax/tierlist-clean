package fr.duboimax.cleanarchi.application.contracts.auth;

public interface PasswordHasher {
    String hash(String plainTextPassword);
    boolean verify(String plainTextPassword, String hashedPassword);
    void fakeVerify();
}
