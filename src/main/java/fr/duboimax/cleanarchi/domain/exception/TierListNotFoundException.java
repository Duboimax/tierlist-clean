package fr.duboimax.cleanarchi.domain.exception;

public class TierListNotFoundException extends DomainException {
    public TierListNotFoundException(String userId) {
        super("TierList not found for user: " + userId);
    }
}
