package fr.duboimax.cleanarchi.application.use_cases.logo;

import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import fr.duboimax.cleanarchi.application.dtos.responses.GetAllLogosResponse;
import fr.duboimax.cleanarchi.application.dtos.responses.GetAllLogosResponse.LogoResponse;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;

import java.util.List;

public class GetAllLogos {
    private final CompanyLogoRepository companyLogoRepository;
    private final LogoUrlBuilder logoUrlBuilder;

    public GetAllLogos(CompanyLogoRepository companyLogoRepository, LogoUrlBuilder logoUrlBuilder) {
        this.companyLogoRepository = companyLogoRepository;
        this.logoUrlBuilder = logoUrlBuilder;
    }

    public GetAllLogosResponse execute() {
        List<CompanyLogo> logos = companyLogoRepository.findAll();

        List<LogoResponse> logoResponses = logos.stream()
                .map(logo -> new LogoResponse(
                        logo.getId().value().toString(),
                        logo.getCompanyName().value(),
                        logoUrlBuilder.buildUrl(logo.getLogoIdentifier())
                ))
                .toList();

        return new GetAllLogosResponse(logoResponses);
    }


}
