package com.epam.eremenko.disk_analizer;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DiskAnalyzer {
    private static String result;
    private static int sCounter = 0;
    private static final List<File> list = new ArrayList<>();


    public static void get(String path, String number) {
        File directory = new File(path);
        readFiles(directory);

        switch (number) {
            case ("1") -> System.out.println(getTheBiggestLetterQuantityFile());
            case ("2") -> System.out.println(getTheFiveBiggestFiles());
            default -> throw new IllegalArgumentException("Unknown choice");
        }
    }

    private static void readFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.stream(files)
                    .peek(file -> {
                        if (file.isFile()) {
                            list.add(file);
                        }
                    })
                    .filter(File::isDirectory)
                    .forEach(DiskAnalyzer::readFiles);
        }
    }

    private static String getTheBiggestLetterQuantityFile() {
        findFileWithMaxSLetters();
        return result;
    }

    private static void findFileWithMaxSLetters() {
        list.stream()
                .filter(file -> file.getName().toLowerCase().contains("s"))
                .forEach(
                        (file) -> {
                            int occurrences = StringUtils.countMatches(file.getName(), "s");
                            if (occurrences > sCounter) {
                                sCounter = occurrences;
                                result = file.getAbsolutePath();
                            }
                        });
    }

    private static String getTheFiveBiggestFiles() {
        StringBuilder sb = new StringBuilder();
        list.sort(Comparator.comparing(File::length));
        for (int i = list.size() - 1; i > list.size() - 6; i--) {
            sb.append(list.get(i).getAbsolutePath()).append("\n");
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        get("/Users/angener/Documents", "2");
    }
}
