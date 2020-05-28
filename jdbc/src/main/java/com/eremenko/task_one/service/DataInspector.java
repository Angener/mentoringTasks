package com.eremenko.task_one.service;

import java.util.List;

public interface DataInspector {
    @Deprecated
    List<String> getTable(String tableName);

    @Deprecated
    List<String> getTableElements(String tableName, String name);

    @Deprecated
    List<String> getAllTables();

    List<String> get(String table);

    List<String> getAll();

    List<String> getElementsByType(String table, String type);
}
