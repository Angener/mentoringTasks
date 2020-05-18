package com.epam.eremenko.FastFileMover;

import java.io.*;

public class FileStreamMover {

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

        int byteCopied;
        try {
            InputStream fis = new FileInputStream(originalFile);
            OutputStream fos = new FileOutputStream(newFile);
            while ((byteCopied = fis.read()) > 0) {
                fos.write(byteCopied);
            }
        } catch (IOException ex) {
            System.err.println("Yuh");
        }
        if (originalFile.delete()) {
            System.out.println("copied");
        }
    }
}
