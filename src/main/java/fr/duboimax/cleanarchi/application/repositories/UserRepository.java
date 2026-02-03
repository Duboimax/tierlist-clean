package fr.duboimax.cleanarchi.application.repositories;

import fr.duboimax.cleanarchi.domain.model.user.Email;
import fr.duboimax.cleanarchi.domain.model.user.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(Email email);
    boolean existsByEmail(Email email);
}
