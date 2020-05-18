package instance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/angener/Desktop/1.txt");
        System.out.println(path.getFileName() + " in " + path.getFileSystem());

        Path pathDir = Paths.get("/Users/angener/Desktop/newDir");
        try {
            Path newDir = Files.createDirectory(pathDir);
            System.out.println(Files.isReadable(newDir));
            System.out.println(Files.isWritable(newDir));
            System.out.println(Files.isExecutable(newDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Files.delete(path);
        Files.delete(pathDir);
    }
}
