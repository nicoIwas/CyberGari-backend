package com.cybergari.mvp.compressor;

import java.io.IOException;

public interface Compressor {
    void compress(String filePath, String fileId) throws IOException;

    void uncompress(String filePath, String fileId) throws IOException;
}
