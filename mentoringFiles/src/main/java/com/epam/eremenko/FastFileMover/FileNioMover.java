package com.epam.eremenko.FastFileMover;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileNioMover {

    public static void moveRename(String exist, String distinct) {
        Path originalFile = Paths.get(exist);
        Path newFile = Paths.get(distinct);

        try {
            Files.move(originalFile, newFile, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
