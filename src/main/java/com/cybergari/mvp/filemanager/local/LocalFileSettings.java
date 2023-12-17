package com.cybergari.mvp.filemanager.local;

public class LocalFileSettings {
    private static final SO soInUse = SO.LINUX;
    public static final String SEPARATOR =  "\\";
    public static final String FOLDER = "C:\\Users\\Note\\Desktop\\USP\\BD2";

    private enum SO {
        WINDOWS,
        LINUX
    }
}
