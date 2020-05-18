package com.epam.eremenko.FastFileMover;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileChanelMover {

    public static void moveRename(String exist, String distinct) {
        final String filename = "file.txt";
        final File originalFile = new File(exist, filename);
        final File newFile = new File(distinct, filename);

        if (originalFile.exists() && !newFile.exists()) {
            try {
                if (newFile.createNewFile()) {
                    System.out.println("New file created");
                }
            } catch (IOException ex) {
                System.err.println("Yuh");
            }
        }

        try (
                FileChannel from = new FileInputStream(originalFile).getChannel();
                FileChannel to = new FileOutputStream(newFile).getChannel()
        ) {
            to.transferFrom(from, 0, from.size());
        } catch (IOException ex) {
            System.out.println("Yuh");
        }
        if (originalFile.delete()) {
            System.out.println("Moved");
        }
    }
}
