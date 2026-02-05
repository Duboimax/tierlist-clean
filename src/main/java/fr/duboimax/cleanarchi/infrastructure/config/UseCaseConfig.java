package fr.duboimax.cleanarchi.infrastructure.config;

import fr.duboimax.cleanarchi.application.contracts.auth.PasswordHasher;
import fr.duboimax.cleanarchi.application.contracts.auth.TokenProvider;
import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import fr.duboimax.cleanarchi.application.contracts.pdf.PdfGenerator;
import fr.duboimax.cleanarchi.application.contracts.storage.FileStorage;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.application.repositories.UserRepository;
import fr.duboimax.cleanarchi.application.use_cases.auth.AuthenticateUser;
import fr.duboimax.cleanarchi.application.use_cases.auth.CreateUser;
import fr.duboimax.cleanarchi.application.use_cases.logo.AddCompanyLogo;
import fr.duboimax.cleanarchi.application.use_cases.logo.GetAllLogos;
import fr.duboimax.cleanarchi.application.use_cases.tierlist.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateUser createUser(UserRepository userRepository, PasswordHasher passwordHasher, TierListRepository tierListRepository) {
        return new CreateUser(userRepository, passwordHasher, tierListRepository);
    }

    @Bean
    public AuthenticateUser authenticateUser(UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        return new AuthenticateUser(userRepository, passwordHasher, tokenProvider);
    }

    @Bean
    public AddCompanyLogo addCompanyLogo(CompanyLogoRepository companyLogoRepository, LogoUrlBuilder logoUrlBuilder) {
        return new AddCompanyLogo(companyLogoRepository,  logoUrlBuilder);
    }

    @Bean
    public GetAllLogos getAllLogos(CompanyLogoRepository companyLogoRepository, LogoUrlBuilder logoUrlBuilder) {
        return new GetAllLogos(companyLogoRepository, logoUrlBuilder);
    }

    @Bean
    public PlaceLogo placeLogo(TierListRepository tierListRepository, CompanyLogoRepository companyLogoRepository) {
        return new PlaceLogo(tierListRepository, companyLogoRepository);
    }

    @Bean
    public GetTierList getTierList(TierListRepository tierListRepository, CompanyLogoRepository companyLogoRepository, LogoUrlBuilder logoUrlBuilder) {
        return new GetTierList(tierListRepository, companyLogoRepository, logoUrlBuilder);
    }

    @Bean
    public RemoveLogo removeLogo(TierListRepository tierListRepository) {
        return new RemoveLogo(tierListRepository);
    }

    @Bean
    public ExportAndDownloadTierList exportAndDownloadTierList(
            TierListRepository tierListRepository,
            CompanyLogoRepository companyLogoRepository,
            PdfGenerator pdfGenerator,
            FileStorage fileStorage
    ) {
        return new ExportAndDownloadTierList(tierListRepository, companyLogoRepository, pdfGenerator, fileStorage);
    }
}