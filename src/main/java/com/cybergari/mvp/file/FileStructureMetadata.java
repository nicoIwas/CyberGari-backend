package com.cybergari.mvp.file;

import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FileStructureMetadata {
    @Getter
    private final Folder root;
    private final Map<String, File> cache;

    public FileStructureMetadata(final Folder root) {
        this.root = root;
        this.cache = new HashMap<>();

        cacheFiles();
    }

    private void cacheFiles() {
        final Stack<Folder> toExplore = new Stack<>();
        toExplore.push(root);

        while(!toExplore.empty()) {
            for(final Storage storage : toExplore.pop().getStorages()) {
                if(storage instanceof Folder)
                    toExplore.add((Folder) storage);

                if(storage instanceof File) {
                    final var f = (File) storage;
                    cache.put(f.getId(), f);
                }
            }
        }
    }

    public File getFileFromId(final String id) {
        return cache.get(id);
    }

    public Collection<File> getAllFiles() {
        return cache.values();
    }
}
