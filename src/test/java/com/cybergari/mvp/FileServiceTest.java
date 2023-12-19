package com.cybergari.mvp;

import com.cybergari.mvp.controller.response.FileResponse;
import com.cybergari.mvp.file.File;
import com.cybergari.mvp.file.FileService;
import com.cybergari.mvp.fixtures.FileFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FileServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void givenAValidContext_WhenGettingAllFiles_ShouldReturnTheCorrectAmount() {
        final List<FileResponse> files = fileService.getAllFiles();
        Assertions.assertFalse(files.isEmpty());
    }


    private List<File> givenValidFileList() {
        final var fileOne = FileFixture.load();
        final var fileTwo = FileFixture.load();
        final var fileThree = FileFixture.load();
        return List.of(fileOne, fileTwo, fileThree);
    }
}
