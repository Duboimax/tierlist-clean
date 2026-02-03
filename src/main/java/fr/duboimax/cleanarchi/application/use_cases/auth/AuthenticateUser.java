package fr.duboimax.cleanarchi.application.use_cases.auth;

import fr.duboimax.cleanarchi.application.contracts.auth.PasswordHasher;
import fr.duboimax.cleanarchi.application.contracts.auth.TokenProvider;
import fr.duboimax.cleanarchi.application.dtos.requests.AuthenticateUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.AuthenticateUserResponse;
import fr.duboimax.cleanarchi.application.repositories.UserRepository;
import fr.duboimax.cleanarchi.domain.exception.InvalidCredentialsException;
import fr.duboimax.cleanarchi.domain.model.user.Email;
import fr.duboimax.cleanarchi.domain.model.user.User;

import java.util.Optional;

public class AuthenticateUser {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public AuthenticateUser(UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    public AuthenticateUserResponse execute(AuthenticateUserRequest request) {
        Email email = new Email(request.email());

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            passwordHasher.fakeVerify();
            throw new InvalidCredentialsException("Invalid email");
        }

        User user = optionalUser.get();

        boolean passwordMatches = passwordHasher.verify(request.password(), user.getPassword().hashedValue());

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid password");
        }

        String token = tokenProvider.generate(user);

        return new AuthenticateUserResponse(
                user.getId().value().toString(),
                user.getEmail().value(),
                token
        );
    }

}
