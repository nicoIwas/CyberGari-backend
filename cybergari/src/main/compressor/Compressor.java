package main.compressor;

import java.io.IOException;

public interface Compressor {
    void compress(String filePath) throws IOException;

    void uncompress(String filePath) throws IOException;
}