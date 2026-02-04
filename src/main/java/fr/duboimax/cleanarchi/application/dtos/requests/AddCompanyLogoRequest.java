package fr.duboimax.cleanarchi.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddCompanyLogoRequest(
        @NotBlank(message = "Nom de l'entreprise requis")
        @Size(max = 100, message = "Nom maximum 100 caractères")
        String companyName,

        @NotBlank(message = "Logo identifier requis")
        String logoIdentifier) {}
