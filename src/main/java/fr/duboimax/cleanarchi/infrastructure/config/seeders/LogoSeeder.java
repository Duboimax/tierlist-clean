package fr.duboimax.cleanarchi.infrastructure.config.seeders;

import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyName;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogoSeeder {

    private final CompanyLogoRepository companyLogoRepository;

    public LogoSeeder(CompanyLogoRepository companyLogoRepository) {
        this.companyLogoRepository = companyLogoRepository;
    }

    @PostConstruct
    public void seed() {
        if (!companyLogoRepository.findAll().isEmpty()) {
            return;
        }

        List<String> companies = List.of(
                "google.com",
                "microsoft.com",
                "amazon.com",
                "netflix.com",
                "spotify.com",
                "twitch.tv",
                "reddit.com",
                "uber.com",
                "apple.com"
        );

        for (String domain : companies) {
            String name = domain.split("\\.")[0];
            String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);

            CompanyLogo logo = new CompanyLogo(new CompanyName(capitalizedName), domain);
            companyLogoRepository.save(logo);
        }

        System.out.println("✅ " + companies.size() + " logos seeded!");
    }
}
