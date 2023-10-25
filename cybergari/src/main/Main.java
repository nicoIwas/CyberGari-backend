package main;

import main.filemanager.local.LocalFileManager;

public class Main {
    public static void main(String... args) {
        final var test = new LocalFileManager("C:\\Users\\Pedro\\Desktop\\EQ_ST");
        final var result = test.getFileStructure();
        System.out.println(test);
    }
}
