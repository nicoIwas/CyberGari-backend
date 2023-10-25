package main.filemanager.local;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import main.files.Folder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalFileUtils {
    public static Folder readFileStructureFromRoot(final String rootPath) {
        Folder ret = null;

        try {
            ret = readFileStructureFromFile(new File(rootPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    private static Folder readFileStructureFromFile(final File localRoot) throws IOException {
        final Folder ret = new Folder();

        for(final File f : localRoot.listFiles()) {
            if(f.isDirectory())
                ret.addStorage(readFileStructureFromFile(f));
            else
                ret.addStorage(fileMetadataToFileDomain(f));
        }

        return ret;
    }

    private static main.files.File fileMetadataToFileDomain(final File f) throws IOException {
        final var att = Files.readAttributes(f.toPath(), BasicFileAttributes.class);

        return new main.files.File(
                f.getAbsolutePath().hashCode() + "",
                att.size(),
                att.creationTime().toInstant(),
                att.lastModifiedTime().toInstant(),
                getFileName(f)
        );
    }

    public static String getFileName(final File file) {
        final var fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
