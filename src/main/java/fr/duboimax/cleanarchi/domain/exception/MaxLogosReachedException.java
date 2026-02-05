package fr.duboimax.cleanarchi.domain.exception;

public class MaxLogosReachedException extends DomainException {
    public MaxLogosReachedException() {
        super("Maximum number of logos reached (10)");
    }
}
