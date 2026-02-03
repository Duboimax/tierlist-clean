package fr.duboimax.cleanarchi.domain.exception;

public class InvalidEmailFormatException extends DomainException {
    public InvalidEmailFormatException(String email) {
        super("Invalid email format: " + email);
    }
}
