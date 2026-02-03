package fr.duboimax.cleanarchi.application.contracts.auth;

import fr.duboimax.cleanarchi.domain.model.user.User;

public interface TokenProvider {
    String generate(User user);
}
