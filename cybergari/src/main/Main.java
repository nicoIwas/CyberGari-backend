package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }

    }

//    public static void main(String... args) throws IOException {
//        final String filepath = "C:\\Users\\Pedro\\Desktop\\EQ_ST";
//        final var manager = new LocalFileManager(new SimpleCompressor(), filepath);
//        final var tagService = new TagService(new TagRepository(), manager);
//
//        /*
//        tagService.updateFileTags(
//                List.of(new FileTagUpdateVO("-912624929", List.of("HIGH_PRI", "RANDOM"))),
//                "reno"
//        );
//
//         */
//
//        final var result = manager.getFileStructure();
//        System.out.println(manager);
//    }
}
