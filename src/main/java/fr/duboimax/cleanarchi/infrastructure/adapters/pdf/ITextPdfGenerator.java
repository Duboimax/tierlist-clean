package fr.duboimax.cleanarchi.infrastructure.adapters.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fr.duboimax.cleanarchi.application.contracts.logo.LogoUrlBuilder;
import fr.duboimax.cleanarchi.application.contracts.pdf.PdfGenerator;
import fr.duboimax.cleanarchi.domain.model.logo.CompanyLogo;
import fr.duboimax.cleanarchi.domain.model.logo.LogoId;
import fr.duboimax.cleanarchi.domain.model.tierlist.Tier;
import fr.duboimax.cleanarchi.domain.model.tierlist.TierList;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class ITextPdfGenerator implements PdfGenerator {

    private final LogoUrlBuilder logoUrlBuilder;

    public ITextPdfGenerator(LogoUrlBuilder logoUrlBuilder) {
        this.logoUrlBuilder = logoUrlBuilder;
    }

    @Override
    public byte[] generate(TierList tierList, List<CompanyLogo> logos) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph title = new Paragraph("MA TIER LIST")
                .setBold()
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(title);

        for (Tier tier : Tier.values()) {
            addTierRow(document, tier, tierList, logos);
        }

        document.close();
        return out.toByteArray();
    }

    private void addTierRow(Document document, Tier tier, TierList tierList, List<CompanyLogo> logos) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{15, 85}));
        table.setWidth(UnitValue.createPercentValue(100));
        table.setMarginBottom(5);

        Cell tierCell = new Cell()
                .add(new Paragraph(tier.name()).setBold().setFontSize(24))
                .setBackgroundColor(getTierColor(tier))
                .setTextAlignment(TextAlignment.CENTER)
                .setMinHeight(60);
        table.addCell(tierCell);

        Cell logosCell = new Cell();
        logosCell.setMinHeight(60);
        logosCell.setBackgroundColor(new DeviceRgb(45, 45, 45));

        Table logosTable = new Table(UnitValue.createPercentArray(getLogoColumns(tierList, tier)));
        logosTable.setWidth(UnitValue.createPercentValue(100));

        boolean hasLogos = false;
        for (Map.Entry<LogoId, Tier> entry : tierList.getPlacements().entrySet()) {
            if (entry.getValue() == tier) {
                LogoId logoId = entry.getKey();

                logos.stream()
                        .filter(l -> l.getId().equals(logoId))
                        .findFirst()
                        .ifPresent(logo -> {
                            Cell logoCell = createLogoCell(logo);
                            logosTable.addCell(logoCell);
                        });
                hasLogos = true;
            }
        }

        if (hasLogos) {
            logosCell.add(logosTable);
        } else {
            logosCell.add(new Paragraph("(vide)")
                    .setItalic()
                    .setFontColor(ColorConstants.GRAY)
                    .setTextAlignment(TextAlignment.CENTER));
        }

        table.addCell(logosCell);
        document.add(table);
    }

    private Cell createLogoCell(CompanyLogo logo) {
        Cell cell = new Cell();
        cell.setPadding(5);
        cell.setBackgroundColor(new DeviceRgb(60, 60, 60));

        try {
            String imageUrl = logoUrlBuilder.buildUrl(logo.getLogoIdentifier());
            ImageData imageData = ImageDataFactory.create(new URL(imageUrl));

            Image image = new Image(imageData);

            image.setWidth(50);
            image.setHeight(50);

            image.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell.add(image);

        } catch (Exception e) {
            cell.add(new Paragraph("🖼")
                    .setFontSize(30)
                    .setTextAlignment(TextAlignment.CENTER));
        }

        cell.add(new Paragraph(logo.getCompanyName().value())
                .setFontSize(8)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER));

        return cell;
    }

    private float[] getLogoColumns(TierList tierList, Tier tier) {
        int count = 0;
        for (Tier t : tierList.getPlacements().values()) {
            if (t == tier) count++;
        }
        if (count == 0) return new float[]{100};

        float[] columns = new float[Math.min(count, 10)];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = 1;
        }
        return columns;
    }

    private DeviceRgb getTierColor(Tier tier) {
        return switch (tier) {
            case S -> new DeviceRgb(255, 127, 127);
            case A -> new DeviceRgb(255, 191, 127);
            case B -> new DeviceRgb(255, 255, 127);
            case C -> new DeviceRgb(127, 255, 127);
            case D -> new DeviceRgb(127, 127, 255);
        };
    }
}