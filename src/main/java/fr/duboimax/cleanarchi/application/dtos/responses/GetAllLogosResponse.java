package fr.duboimax.cleanarchi.application.dtos.responses;

import java.util.List;

public record GetAllLogosResponse(List<LogoResponse> logos) {

    public record LogoResponse(String logoId, String companyName, String logoUrl) {}
}
