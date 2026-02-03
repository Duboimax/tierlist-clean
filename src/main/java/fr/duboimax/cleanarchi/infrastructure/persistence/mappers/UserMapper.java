package fr.duboimax.cleanarchi.infrastructure.persistence.mappers;

import fr.duboimax.cleanarchi.domain.model.user.Email;
import fr.duboimax.cleanarchi.domain.model.user.Password;
import fr.duboimax.cleanarchi.domain.model.user.User;
import fr.duboimax.cleanarchi.domain.model.user.UserId;
import fr.duboimax.cleanarchi.infrastructure.persistence.entities.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().value().toString(),
                user.getEmail().value(),
                user.getPassword().hashedValue(),
                user.getCreatedAt()
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                new UserId(java.util.UUID.fromString(entity.getId())),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                entity.getCreatedAt()
        );
    }
}
