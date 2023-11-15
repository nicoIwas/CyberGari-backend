package main.filemanager.local;

public class LocalFileSettings {
    private static final SO soInUse = SO.WINDOWS;
    public static final String SEPARATOR = soInUse == SO.WINDOWS ? "\\" : "/";
    public static final String FOLDER = soInUse == SO.WINDOWS ? "C:\\Users\\Pedro\\Desktop\\EQ_ST" : "/home/nicolinux/test/";

    private enum SO {
        WINDOWS,
        LINUX
    }
}
