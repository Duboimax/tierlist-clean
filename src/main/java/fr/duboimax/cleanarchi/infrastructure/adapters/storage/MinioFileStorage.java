package fr.duboimax.cleanarchi.infrastructure.adapters.storage;

import fr.duboimax.cleanarchi.application.contracts.storage.FileStorage;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class MinioFileStorage implements FileStorage {

    private final MinioClient minioClient;
    private final String bucketName;

    public MinioFileStorage(
            @Value("${minio.endpoint}") String endpoint,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.bucket-name}") String bucketName
    ) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;
    }

    @PostConstruct
    public void init() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize MinIO bucket", e);
        }
    }

    @Override
    public String store(byte[] content, String filename) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .stream(new ByteArrayInputStream(content), content.length, -1)
                            .contentType("application/pdf")
                            .build()
            );
            return filename;
        } catch (Exception e) {
            throw new RuntimeException("Could not store file in MinIO", e);
        }
    }

    @Override
    public byte[] retrieve(String filename) {
        try {
            GetObjectResponse response = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .build()
            );
            return response.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Could not retrieve file from MinIO", e);
        }
    }
}