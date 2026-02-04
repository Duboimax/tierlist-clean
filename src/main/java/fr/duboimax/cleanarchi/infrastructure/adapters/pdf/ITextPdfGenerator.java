package fr.duboimax.cleanarchi.infrastructure.adapters.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import fr.duboimax.cleanarchi.application.contracts.pdf.PdfGenerator;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.Tier;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Component
public class ITextPdfGenerator implements PdfGenerator {

    @Override
    public byte[] generate(TierList tierList, List<CompanyLogo> logos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("TIER LIST").setBold().setFontSize(24));

        for (Tier tier : Tier.values()) {
            document.add(new Paragraph(tier.name() + " - " + tier.getLabel())
                    .setBold()
                    .setFontSize(16)
                    .setMarginTop(20));

            boolean hasLogos = false;
            for (Map.Entry<LogoId, Tier> entry : tierList.getPlacements().entrySet()) {
                if (entry.getValue() == tier) {
                    LogoId logoId = entry.getKey();
                    logos.stream()
                            .filter(l -> l.getId().equals(logoId))
                            .findFirst()
                            .ifPresent(logo -> {
                                document.add(new Paragraph("  • " + logo.getCompanyName().value()).setFontSize(12));
                            });
                    hasLogos = true;
                }
            }

            if (!hasLogos) {
                document.add(new Paragraph("  (vide)").setItalic().setFontSize(10));
            }
        }

        document.close();
        return out.toByteArray();
    }
}
