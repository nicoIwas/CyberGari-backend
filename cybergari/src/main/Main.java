package main;

import main.compressor.SimpleCompressor;
import main.filemanager.local.LocalFileManager;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
        final String filepath = "C:\\Users\\Pedro\\Desktop\\EQ_ST";
        final var test = new LocalFileManager(new SimpleCompressor(), filepath);
        final var result = test.getFileStructure();
        System.out.println(test);
    }
}
