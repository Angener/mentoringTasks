package com.epam.eremenko.FastFileMover;

import java.io.*;

public class FileBufferedStreamMover {

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
                System.err.println("Decline");
            }
        }

        int copiedBytes;
        byte[] buffer = new byte[100];
        try (InputStream fis = new BufferedInputStream(new FileInputStream(originalFile));
             OutputStream fos = new BufferedOutputStream(new FileOutputStream(newFile))) {
            while ((copiedBytes = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, copiedBytes);
            }
            fos.write(fis.read());
        } catch (IOException ex) {
            System.err.println("Decline");
        }

        if (originalFile.delete()) {
            System.out.println("Copied");
        }
    }
}
