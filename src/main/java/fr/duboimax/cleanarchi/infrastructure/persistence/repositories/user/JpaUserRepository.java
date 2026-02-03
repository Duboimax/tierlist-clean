package fr.duboimax.cleanarchi.infrastructure.persistence.repositories.user;

import fr.duboimax.cleanarchi.application.repositories.UserRepository;
import fr.duboimax.cleanarchi.domain.model.user.Email;
import fr.duboimax.cleanarchi.domain.model.user.User;
import fr.duboimax.cleanarchi.infrastructure.persistence.mappers.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringUserRepository springRepository;

    public JpaUserRepository(SpringUserRepository springRepo) {
        this.springRepository = springRepo;
    }

    @Override
    public void save(User user) {
        springRepository.save(UserMapper.toEntity(user));
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return springRepository.findByEmail(email.value())
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return springRepository.existsByEmail(email.value());
    }
}
