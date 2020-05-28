package com.eremenko.task_one.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class Printer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Printer.class);

    private Printer() {
    }

    public static void print(List<String> list) {
        if (list == null) {
            LOGGER.warn("NPE!!!");
        } else
            for (String s : list){
                LOGGER.info(s);
            }
    }
}
