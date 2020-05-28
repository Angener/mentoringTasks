package com.eremenko.task_two.dao;

public final class DatabaseAccessorFactory {
    private static volatile DatabaseAccessorFactory instance;
    private final DatabaseAccessor databaseAccessor = new DatabaseAccessorImpl();

    private DatabaseAccessorFactory() {
    }

    public static DatabaseAccessorFactory getInstance() {
        if (instance == null) {
            synchronized (DatabaseAccessorFactory.class) {
                if (instance == null) {
                    instance = new DatabaseAccessorFactory();
                }
            }
        }
        return instance;
    }

    public DatabaseAccessor getDatabaseAccessor() {
        return databaseAccessor;
    }
}
