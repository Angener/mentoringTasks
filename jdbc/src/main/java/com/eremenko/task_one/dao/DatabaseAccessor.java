package com.eremenko.task_one.dao;

import java.util.List;

public interface DatabaseAccessor {
    @Deprecated
    List<String> getTable(String tableName) throws DataBaseAccessorException;

    @Deprecated
    List<String> getTableElements(String tableName, String element) throws DataBaseAccessorException;

    @Deprecated
    List<String> getAllTables() throws DataBaseAccessorException;

    List<String> get(String table) throws DataBaseAccessorException;

    List<String> getDatabase() throws DataBaseAccessorException;

    List<String> getElementsByType(String table, String type) throws DataBaseAccessorException;
}
