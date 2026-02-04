package fr.duboimax.cleanarchi.application.contracts.storage;

public interface FileStorage {
    String store(byte[] content, String filename);
    byte[] retrieve(String filename);
}
