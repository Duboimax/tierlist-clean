package fr.duboimax.cleanarchi.infrastructure.web.controllers.logo;

import fr.duboimax.cleanarchi.application.dtos.requests.AddCompanyLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.AddCompanyLogoResponse;
import fr.duboimax.cleanarchi.application.use_cases.logo.AddCompanyLogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddLogoController {

    private final AddCompanyLogo addCompanyLogo;

    public AddLogoController(AddCompanyLogo addCompanyLogo) {
        this.addCompanyLogo = addCompanyLogo;
    }

    @PostMapping("/logos")
    public ResponseEntity<AddCompanyLogoResponse> addLogo(@RequestBody AddCompanyLogoRequest request) {
        return ResponseEntity.ok(addCompanyLogo.execute(request));
    }
}
