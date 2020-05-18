package com.epam.eremenko.disk_analizer;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
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
        checkInput(directory, number);
        readFiles(directory);
        applyFunction(number);
    }

    private static void checkInput(File directory, String number) {
        List<String> validNumbers = Arrays.asList("1", "2", "3", "4");
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Wrong directory: " + directory);
        } else if (!validNumbers.contains(number)) {
            throw new IllegalArgumentException("Incorrect function number: " + number);
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

    private static void applyFunction(String number) {
        switch (number) {
            case ("1") -> printToFile(getTheBiggestLetterQuantityFile());
            case ("2") -> printToFile(getTheFiveBiggestFiles());
            case ("3") -> printToFile(getAverageSizeFilesOfDirectory());
            case ("4") -> printToFile(getAlphabetQuantity());
            default -> throw new IllegalArgumentException("Unknown choice");
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

    private static String getAverageSizeFilesOfDirectory() {
        long sizeCounter = 0;
        int fileCounter = 0;
        for (File file : list) {
            if (file.isFile()) {
                sizeCounter += file.length();
                fileCounter++;
            }
        }
        return String.valueOf(sizeCounter / fileCounter);
    }

    private static String getAlphabetQuantity() {
        int startWithA = 0;
        int startWithB = 0;

        for (File file : list) {
            if (file.getName().toLowerCase().startsWith("a")) {
                startWithA++;
            } else if (file.getName().toLowerCase().startsWith("b")) {
                startWithB++;
            }
        }
        return "Start with \"A\" files: " + startWithA +
                "\n Start with \"B\" files: " + startWithB;
    }

    private static void printToFile(String result) {
        try (Writer writer = new FileWriter("target/save/diskAnalyzer.txt")) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
