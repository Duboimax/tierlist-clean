package fr.duboimax.cleanarchi.application.use_cases.tierlist;

import fr.duboimax.cleanarchi.application.contracts.pdf.PdfGenerator;
import fr.duboimax.cleanarchi.application.contracts.storage.FileStorage;
import fr.duboimax.cleanarchi.application.dtos.responses.ExportTierListResponse;
import fr.duboimax.cleanarchi.application.repositories.CompanyLogoRepository;
import fr.duboimax.cleanarchi.application.repositories.TierListRepository;
import fr.duboimax.cleanarchi.domain.exception.TierListNotFoundException;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import fr.duboimax.cleanarchi.domain.model.user.UserId;

import java.util.List;
import java.util.UUID;

public class ExportTierList {

    private final TierListRepository tierListRepository;
    private final CompanyLogoRepository companyLogoRepository;
    private final PdfGenerator pdfGenerator;
    private final FileStorage fileStorage;

    public ExportTierList(
            TierListRepository tierListRepository,
            CompanyLogoRepository companyLogoRepository,
            PdfGenerator pdfGenerator,
            FileStorage fileStorage
    ) {
        this.tierListRepository = tierListRepository;
        this.companyLogoRepository = companyLogoRepository;
        this.pdfGenerator = pdfGenerator;
        this.fileStorage = fileStorage;
    }

    public ExportTierListResponse execute(UserId userId) {
        TierList tierList = tierListRepository.findByUserId(userId)
                .orElseThrow(() -> new TierListNotFoundException(userId.value().toString()));

        List<CompanyLogo> logos = companyLogoRepository.findAll();

        byte[] pdfContent = pdfGenerator.generate(tierList, logos);

        String filename = "tierlist-" + userId.value().toString() + "-" + UUID.randomUUID() + ".pdf";
        fileStorage.store(pdfContent, filename);

        return new ExportTierListResponse(filename);
    }
}
