package com.eremenko.task_one.service;

public final class DataInspectorFactory {
    private static volatile DataInspectorFactory instance;
    private final DataInspector dataInspector = new DataInspectorImpl();

    private DataInspectorFactory() {
    }

    public static DataInspectorFactory getInstance() {
        if (instance == null) {
            synchronized (DataInspectorFactory.class) {
                if (instance == null) {
                    instance = new DataInspectorFactory();
                }
            }
        }
        return instance;
    }

    public DataInspector getDataInspector() {
        return dataInspector;
    }

}
