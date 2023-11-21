package main.filemanager.local;

import main.file.FileStructureMetadata;
import main.file.Folder;
import main.tag.vos.Priority;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static main.filemanager.local.LocalFileMetaDataManager.METADATA_FILE_NAME;

public class LocalFileExecutor {
    private final LocalFileMetaDataManager localFileMetaDataManager;
    private final FileStructureMetadata structureMetadata;
    private final Map<String, String> filePathCache;
    private final LocalFileMetaDataMapper mapper;

    public LocalFileExecutor(final String rootPath) {
        this.filePathCache = new HashMap<>();
        this.localFileMetaDataManager = new LocalFileMetaDataManager(rootPath);

        final var root = readFileStructureFromRoot(rootPath);
        this.structureMetadata = new FileStructureMetadata(root);

        this.mapper = new LocalFileMetaDataMapper();
    }

    public Collection<main.file.File> getAllFilesMetadata() {
        return this.structureMetadata.getAllFiles();
    }

    public Folder getFileStructure() {
        return this.structureMetadata.getRoot();
    }

    public String getFilePathFromId(final String id) {
        return filePathCache.get(id);
    }

    private Folder readFileStructureFromRoot(final String rootPath) {
        Folder ret = null;

        try {
            ret = readFileStructureFromFile(new File(rootPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    private Folder readFileStructureFromFile(final File localRoot) throws IOException {
        final Folder ret = new Folder();

        for(final File f : localRoot.listFiles()) {
            if(f.isDirectory())
                ret.addStorage(readFileStructureFromFile(f));
            else if (!METADATA_FILE_NAME.equals(f.getName())){
                final var domainFile = fileMetadataToFileDomain(f);
                ret.addStorage(domainFile);
                filePathCache.put(domainFile.getId(), f.getAbsolutePath());
            }
        }

        return ret;
    }

    private main.file.File fileMetadataToFileDomain(final File f) throws IOException {
        final var fileId = getFileId(f);
        final var att = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
        final var extraMetaData = this.localFileMetaDataManager.getMetaData(fileId);

        return new main.file.File(
                fileId,
                getFileName(f),
                att.size(),
                att.creationTime().toInstant(),
                att.lastModifiedTime().toInstant(),
                getFileExtension(f),
                new Priority(extraMetaData.map(LocalFileMetaData::getTags).orElse(Collections.emptyList())),
                extraMetaData.map(LocalFileMetaData::isCompressed).orElse(false)
        );
    }

    private String getFileName(final File file) {
        final var fileName = file.getName();
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    private String getFileExtension(final File file) {
        final var fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String getFileId(final File file) {
        final var filePath = file.getAbsolutePath();
        return filePath.substring(0, filePath.lastIndexOf(".")).hashCode() + "";
    }

    public boolean deleteFile(final String fileId) {
        final String filePath = getFilePathFromId(fileId);
        final File toDelete = new File(filePath);
        return toDelete.delete();
    }

    public void persistMetadata(final Collection<main.file.File> toUpdate) {
        toUpdate.forEach(domain -> {
            final var extraMetadata = mapper.toMetadata(domain);
            localFileMetaDataManager.putMetaData(extraMetadata);
        });

        localFileMetaDataManager.persistMetaData();
    }
}
