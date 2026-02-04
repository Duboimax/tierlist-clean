package fr.duboimax.cleanarchi.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Email requis")
        @Email(message = "Format email invalide")
        String email,

        @NotBlank(message = "Mot de passe requis")
        @Size(min = 8, message = "Mot de passe minimum 8 caractères")
        String password) {
}
