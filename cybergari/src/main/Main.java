package main;

import main.compressor.SimpleCompressor;
import main.filemanager.local.LocalFileManager;
import main.tag.TagRepository;
import main.tag.TagService;
import main.tag.vos.FileTagUpdateVO;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String... args) throws IOException {
        final String filepath = "C:\\Users\\Pedro\\Desktop\\EQ_ST";
        final var manager = new LocalFileManager(new SimpleCompressor(), filepath);
        final var tagService = new TagService(new TagRepository(), manager);

        /*
        tagService.updateFileTags(
                List.of(new FileTagUpdateVO("-912624929", List.of("HIGH_PRI", "RANDOM"))),
                "reno"
        );

         */

        final var result = manager.getFileStructure();
        System.out.println(manager);
    }
}
