package main.filemanager.local;

import lombok.Getter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class LocalFileMetaDataManager {
    public static final String METADATA_FILE_NAME = "cybergari.metadata";

    private final String metadataPath;
    private final Map<String, LocalFileMetaData> filesMetadata;

    public LocalFileMetaDataManager(final String root) {
        this.metadataPath = root + LocalFileSettings.SEPARATOR + METADATA_FILE_NAME;
        this.filesMetadata = getFilesMetadata();
        System.out.println(filesMetadata.toString());
    }

    public Map<String, LocalFileMetaData> getFilesMetadata() {
        try (ObjectInputStream reader = new ObjectInputStream(Files.newInputStream(Paths.get(this.metadataPath)))) {
            final var metaDataList = (List<LocalFileMetaData>) reader.readObject();
            return metaDataList.stream().collect(Collectors.toMap(LocalFileMetaData::getFileId, Function.identity()));
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return new HashMap<>();
    }

    public void persistMetaData() {
        try (ObjectOutputStream writer = new ObjectOutputStream(Files.newOutputStream(Paths.get(this.metadataPath)))) {
            writer.writeObject(new LinkedList<>(filesMetadata.values()));
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void putMetaData(final LocalFileMetaData metaData) {
        this.filesMetadata.put(metaData.getFileId(), metaData);
    }

    public Optional<LocalFileMetaData> getMetaData(final String fileId) {
        return Optional.ofNullable(this.filesMetadata.get(fileId));
    }
}
