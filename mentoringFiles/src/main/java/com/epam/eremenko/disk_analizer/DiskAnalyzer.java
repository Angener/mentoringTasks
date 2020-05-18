package com.epam.eremenko.disk_analizer;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class DiskAnalyzer {
    private static String result;
    private static int sCounter = 0;
    private static final List<File> list = new ArrayList<>();
    private static final Consumer<List<File>> theBiggestLettersQuantity = (list) ->
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

    private static final Consumer<List<File>> theFiveBiggestFiles = (list) -> {
        list.stream()

                .sorted(Comparator.comparingLong(File::length))
                .peek(System.out::println)
                .takeWhile(n -> n.equals(list.get(6)))
                .peek(file -> System.out.println(file.getName()))
                .forEach(file -> result += file.getName() + "\n");
    };



    public static void get(String path, String number) {
        File directory = new File(path);
        readFiles(directory);

        switch (number) {
            case ("1") -> theBiggestLettersQuantity.accept(list);
            case ("2") -> theFiveBiggestFiles.accept(list);
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


    public static void main(String[] args) {


        get("/Users/angener/Documents", "2");
        System.out.println(result);
    }
}
