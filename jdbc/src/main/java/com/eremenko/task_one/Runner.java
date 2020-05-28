package com.eremenko.task_one;

import com.eremenko.task_one.service.DataInspector;
import com.eremenko.task_one.service.DataInspectorFactory;
import com.eremenko.task_one.view.Printer;

public class Runner {
    public static void main(String[] args) {
        DataInspector data = DataInspectorFactory.getInstance().getDataInspector();

        Printer.print(data.get("animals"));
        Printer.print(data.getElementsByType("animals", "Dog"));
        Printer.print(data.getAll());
    }
}
