package fr.duboimax.cleanarchi.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record RemoveLogoRequest(
        @NotBlank(message = "Logo ID requis")
        String logoId
) {}

