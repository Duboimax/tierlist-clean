package fr.duboimax.cleanarchi.infrastructure.web.controllers.auth;

import fr.duboimax.cleanarchi.application.dtos.requests.AuthenticateUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.AuthenticateUserResponse;
import fr.duboimax.cleanarchi.application.use_cases.auth.AuthenticateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Authentification (register, login)")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticateUser authenticateUser;

    public LoginController(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @Operation(summary = "Se connecter")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Connexion réussie"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Identifiants invalides", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<AuthenticateUserResponse>> login(@Valid @RequestBody AuthenticateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, authenticateUser.execute(request)));
    }
}
