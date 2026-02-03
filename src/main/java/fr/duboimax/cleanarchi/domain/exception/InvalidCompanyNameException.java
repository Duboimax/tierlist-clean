package fr.duboimax.cleanarchi.domain.exception;

public class InvalidCompanyNameException extends DomainException {
    public InvalidCompanyNameException(String reason) {
        super("Invalid company name: " + reason);
    }
}
