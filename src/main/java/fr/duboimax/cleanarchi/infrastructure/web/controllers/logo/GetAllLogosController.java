package fr.duboimax.cleanarchi.infrastructure.web.controllers.logo;

import fr.duboimax.cleanarchi.application.dtos.responses.ApiResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.GetAllLogosResponse;
import fr.duboimax.cleanarchi.application.use_cases.logo.GetAllLogos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logos")
public class GetAllLogosController {

    private final GetAllLogos getAllLogos;

    public GetAllLogosController(GetAllLogos getAllLogos) {
        this.getAllLogos = getAllLogos;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<GetAllLogosResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(200, getAllLogos.execute()));
    }
}
