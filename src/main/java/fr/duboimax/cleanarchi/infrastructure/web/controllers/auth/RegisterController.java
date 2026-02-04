package fr.duboimax.cleanarchi.infrastructure.web.controllers.auth;

import fr.duboimax.cleanarchi.application.dtos.requests.RegisterUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.RegisterUserResponse;
import fr.duboimax.cleanarchi.application.use_cases.auth.CreateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Authentification (register, login)")
@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final CreateUser createUser;

    public RegisterController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @Operation(summary = "Créer un compte")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Utilisateur créé"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Format email invalide", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Email déjà existant", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(@Valid  @RequestBody RegisterUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, createUser.execute(request)));
    }


}
