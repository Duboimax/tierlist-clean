package fr.duboimax.cleanarchi.infrastructure.web.controllers.auth;

import fr.duboimax.cleanarchi.application.dtos.requests.RegisterUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.RegisterUserResponse;
import fr.duboimax.cleanarchi.application.use_cases.auth.CreateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final CreateUser createUser;

    public RegisterController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(@RequestBody RegisterUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(201, createUser.execute(request)));
    }


}
