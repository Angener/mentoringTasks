package com.epam.eremenko.FastFileMover;

import java.io.File;
import java.nio.file.Path;

public class FileRenameMover {

    public static void moveRename(String exist, String distinct) {
        final String filename = "file.txt";
        final File originalFile = new File(exist, filename);
        final File newFile = new File(distinct, filename);
        if (originalFile.exists() && !newFile.exists()) {
            System.out.println("Koпиpyeм фaйл " + filename);
            if (originalFile.renameTo(newFile)) {
                System.out.println("success");
            } else {
                System.out.println("failed");
            }
        }
    }


}