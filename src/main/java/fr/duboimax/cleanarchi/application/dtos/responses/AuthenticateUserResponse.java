package fr.duboimax.cleanarchi.application.dtos.responses;

public record AuthenticateUserResponse(String userId, String email, String token) {
}
