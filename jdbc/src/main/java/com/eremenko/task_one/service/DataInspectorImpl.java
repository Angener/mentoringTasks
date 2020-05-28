package com.eremenko.task_one.service;

import com.eremenko.task_one.dao.DataBaseAccessorException;
import com.eremenko.task_one.dao.DatabaseAccessor;
import com.eremenko.task_one.dao.DatabaseAccessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DataInspectorImpl implements DataInspector {
    private final Logger LOGGER = LoggerFactory.getLogger(DataInspectorFactory.class);
    private final DatabaseAccessor databaseAccessor = DatabaseAccessorFactory.getInstance().getDatabaseAccessor();
    private static List<String> result;

    @Override
    public List<String> get(String table) {
        try {
            result = databaseAccessor.get(table);
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<String> getAll() {
        try {
            result = databaseAccessor.getDatabase();
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<String> getElementsByType(String table, String type) {
        try {
            result = databaseAccessor.getElementsByType(table, type);
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    @Deprecated
    @Override
    public List<String> getTable(String tableName) {
        try {
            result = databaseAccessor.getTable(tableName);
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    @Deprecated
    @Override
    public List<String> getTableElements(String tableName, String element) {
        try {
            result = databaseAccessor.getTableElements(tableName, element);
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }

    @Deprecated
    @Override
    public List<String> getAllTables() {
        try {
            result = databaseAccessor.getAllTables();
        } catch (DataBaseAccessorException ex) {
            LOGGER.error(ex.getMessage());
        }
        return result;
    }
}
