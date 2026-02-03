package fr.duboimax.cleanarchi.application.use_cases.logo;

import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import fr.duboimax.cleanarchi.application.dtos.requests.AddCompanyLogoRequest;
import fr.duboimax.cleanarchi.application.dtos.responses.AddCompanyLogoResponse;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.domain.exception.LogoAlreadyExistsException;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;

public class AddCompanyLogo {

    private final CompanyLogoRepository companyLogoRepository;
    private final LogoUrlBuilder logoUrlBuilder;


    public AddCompanyLogo(CompanyLogoRepository companyLogoRepository,  LogoUrlBuilder logoUrlBuilder) {
        this.companyLogoRepository = companyLogoRepository;
        this.logoUrlBuilder = logoUrlBuilder;
    }

    public AddCompanyLogoResponse execute(AddCompanyLogoRequest request) {
        CompanyName companyName = new CompanyName(request.companyName());

        if (companyLogoRepository.existsByCompanyName(companyName)) {
            throw new LogoAlreadyExistsException(companyName.value());
        }

        CompanyLogo newCompanyLogo = new CompanyLogo(
                companyName,
                request.logoIdentifier()
        );

        this.companyLogoRepository.save(newCompanyLogo);

        String fullUrl = logoUrlBuilder.buildUrl(newCompanyLogo.getLogoIdentifier());

        return new AddCompanyLogoResponse(
                newCompanyLogo.getId().value().toString(),
                newCompanyLogo.getCompanyName().value(),
                fullUrl
        );
    }
}
