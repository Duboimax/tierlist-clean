package fr.duboimax.cleanarchi.application.contracts.pdf;

import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;

import java.util.List;

public interface PdfGenerator {
    byte[] generate(TierList tierList, List<CompanyLogo> logos);
}
