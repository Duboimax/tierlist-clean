package fr.duboimax.cleanarchi.application.use_cases.auth;

import fr.duboimax.cleanarchi.application.contracts.auth.PasswordHasher;
import fr.duboimax.cleanarchi.application.dtos.requests.RegisterUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.RegisterUserResponse;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.application.repositories.UserRepository;
import fr.duboimax.cleanarchi.domain.exception.EmailAlreadyExistsException;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.Email;
import fr.duboimax.cleanarchi.domain.model.user.Password;
import fr.duboimax.cleanarchi.domain.model.user.User;

public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TierListRepository tierListRepository;


    public CreateUser(UserRepository userRepository, PasswordHasher passwordHasher,  TierListRepository tierListRepository) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tierListRepository = tierListRepository;
    }

    public RegisterUserResponse execute(RegisterUserRequest request) {
        Email email = new Email(request.email());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(request.email());
        }

        String hashedPassword = passwordHasher.hash(request.password());
        Password password = new Password(hashedPassword);

        User user = new User(email, password);
        userRepository.save(user);

        TierList tierList = new TierList(user.getId());
        tierListRepository.save(tierList);

        return new RegisterUserResponse(
                user.getId().value().toString(),
                user.getEmail().value()
        );
    }
}
