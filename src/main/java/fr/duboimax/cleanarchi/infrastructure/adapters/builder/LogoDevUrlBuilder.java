package fr.duboimax.cleanarchi.infrastructure.adapters.builder;

import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogoDevUrlBuilder implements LogoUrlBuilder {
    private final String baseUrl;
    private final String token;

    public LogoDevUrlBuilder(
            @Value("${logodev.base-url}") String baseUrl,
            @Value("${logodev.token}") String token
    ) {
        this.baseUrl = baseUrl;
        this.token = token;
    }

    @Override
    public String buildUrl(String logoIdentifier) {
        return baseUrl + "/" + logoIdentifier + "?token=" + token;
    }
}
