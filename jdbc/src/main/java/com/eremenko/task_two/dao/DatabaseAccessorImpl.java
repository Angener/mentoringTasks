package com.eremenko.task_two.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseAccessorImpl implements DatabaseAccessor {
    private static final List<String> result = new ArrayList<>();


    private void connect(String query, Applier app) throws SQLException, IOException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            app.apply(resultSet);
        }
    }

    public void create(){

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
