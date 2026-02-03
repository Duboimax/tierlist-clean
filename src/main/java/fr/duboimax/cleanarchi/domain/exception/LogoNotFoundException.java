package fr.duboimax.cleanarchi.domain.exception;

public class LogoNotFoundException extends DomainException {
    public LogoNotFoundException(String logoId) {
        super("Logo not found: " + logoId);
    }
}
