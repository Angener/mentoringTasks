package com.eremenko.task_one.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseAccessorImpl implements DatabaseAccessor {
    private static final List<String> result = new ArrayList<>();

    @Override
    public List<String> getElementsByType(String table, String type) throws DataBaseAccessorException {
        result.clear();
        try {
            getElement(table, type);
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    private void getElement(String table, String type) throws SQLException, IOException {
        connectPrepared("SELECT * FROM " + table + " WHERE type = ?", type,
                (s) -> {
                    while (s.next()) {
                        result.add(s.getString(1));
                    }
                });
    }

    private void connectPrepared(String query, String element, Applier app) throws
            SQLException, IOException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, element);
            ResultSet resultSet = preparedStatement.executeQuery();
            app.apply(resultSet);
        }
    }

    @Override
    public List<String> get(String table) throws DataBaseAccessorException {
        result.clear();
        try {
            collectTableRows(table);
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    private void collectTableRows(String table) throws SQLException, IOException {
        connect("SELECT * FROM " + table, (s) -> {
            while (s.next()) {
                result.add(s.getString(1) + " " + s.getString(2));
            }
        });
    }

    private void connect(String query, Applier app) throws SQLException, IOException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            app.apply(resultSet);
        }
    }

    @Override
    public List<String> getDatabase() throws DataBaseAccessorException {
        result.clear();
        try {
            getAll();
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    private void getAll() throws SQLException, IOException {
        connect("SHOW TABLES", (s) -> {
            while (s.next()) {
                result.add("***** Table: " + s.getString(1) + " *****");
                collectTableRows(s.getString(1));
            }
        });
    }

    @Deprecated
    @Override
    public List<String> getTable(String tableName) throws DataBaseAccessorException {
        result.clear();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            while (resultSet.next()) {
                result.add(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    @Deprecated
    @Override
    public List<String> getTableElements(String tableName, String element) throws DataBaseAccessorException {
        result.clear();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM " + tableName + " WHERE type = ?")) {
            preparedStatement.setString(1, element);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    @Deprecated
    @Override
    public List<String> getAllTables() throws DataBaseAccessorException {
        result.clear();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW TABLES")) {
            while (resultSet.next()) {
                result.add("***** Table: " + resultSet.getString(1) + " *****");
                getTable(resultSet.getString(1));
            }
        } catch (SQLException | IOException ex) {
            throw new DataBaseAccessorException(ex.getMessage());
        }
        return result;
    }

    private Connection getConnection() throws SQLException, IOException {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/jdbc.properties")) {
            prop.load(in);
        }
        return DriverManager.getConnection(prop.getProperty("jdbc.url"), prop.getProperty("jdbc.username"),
                prop.getProperty("jdbc.password"));
    }
}
