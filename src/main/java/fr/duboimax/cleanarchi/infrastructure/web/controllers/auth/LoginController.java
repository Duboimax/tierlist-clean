package fr.duboimax.cleanarchi.infrastructure.web.controllers.auth;

import fr.duboimax.cleanarchi.application.dtos.requests.AuthenticateUserRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.AuthenticateUserResponse;
import fr.duboimax.cleanarchi.application.use_cases.auth.AuthenticateUser;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticateUser authenticateUser;

    public LoginController(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @PostMapping("/login")
    public  ResponseEntity<ApiResponse<AuthenticateUserResponse>> login(@RequestBody AuthenticateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, authenticateUser.execute(request)));
    }
}
