package fr.duboimax.cleanarchi.domain.exception;

public class LogoAlreadyExistsException extends DomainException {
    public LogoAlreadyExistsException(String companyName) {
        super("Logo already exists for company: " + companyName);
    }
}
