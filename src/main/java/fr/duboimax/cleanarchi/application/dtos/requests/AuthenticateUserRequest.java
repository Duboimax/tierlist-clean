package fr.duboimax.cleanarchi.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticateUserRequest(
        @NotBlank(message = "Email requis")
        @Email(message = "Format email invalide")
        String email,

        @NotBlank(message = "Mot de passe requis")
        String password) {
}
