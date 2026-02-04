package fr.duboimax.cleanarchi.infrastructure.web.controllers.tierlist;

import fr.duboimax.cleanarchi.application.contracts.storage.FileStorage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tierlist")
public class DownloadPdfController {

    private final FileStorage fileStorage;

    public DownloadPdfController(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        byte[] content = fileStorage.retrieve(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }
}
