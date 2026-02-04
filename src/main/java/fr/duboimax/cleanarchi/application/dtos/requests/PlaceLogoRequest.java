package fr.duboimax.cleanarchi.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PlaceLogoRequest(
        @NotBlank(message = "Logo ID requis")
        String logoId,

        @NotBlank(message = "Tier requis")
        @Pattern(regexp = "^[SABCD]$", message = "Tier invalide (S, A, B, C ou D)")
        String tier) {}

