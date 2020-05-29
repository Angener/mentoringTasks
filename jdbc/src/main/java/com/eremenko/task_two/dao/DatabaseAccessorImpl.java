package com.eremenko.task_two.dao;


import com.eremenko.task_two.entity.Customer;
import com.eremenko.task_two.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseAccessorImpl implements DatabaseAccessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseAccessorImpl.class);

    @Override
    public void createTables() throws DataBaseAccessorException {
        try {
            madeTables();
        } catch (SQLException | IOException ex) {
            LOGGER.debug(ex.getMessage());
            throw new DataBaseAccessorException(ex.getMessage());
        }
    }

    private void madeTables() throws SQLException, IOException {
        String customers = "CREATE TABLE IF NOT EXISTS customers " +
                "(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(40) NOT NULL)";
        String orders = "CREATE TABLE IF NOT EXISTS orders (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                " customerId INT NOT NULL, date DATETIME, FOREIGN KEY (customerId) REFERENCES customers (id))";
        connect(customers, null, (p) -> {
            p.addBatch();
            p.addBatch(orders);
            p.executeBatch();
        });
    }

    private void connect(String query, DaoTransferObject dto, Applier app) throws
            SQLException, IOException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            app.apply(preparedStatement);
        }
    }

    @Override
    public void create(DaoTransferObject dto) throws DataBaseAccessorException{
        try {
            switchMethod(dto);
        } catch (SQLException | IOException ex){
            LOGGER.debug(ex.getMessage());
            throw new DataBaseAccessorException(ex.getMessage());
        }
    }

    private void switchMethod(DaoTransferObject dto) throws
            SQLException, IOException, DataBaseAccessorException {
        switch (dto.getType()){
            case ("Customer") -> createCustomer(dto);
            case ("Order") -> createOrder(dto);
            default -> throw new DataBaseAccessorException("Unknown type");
        }
    }

    private void createCustomer (DaoTransferObject newCustomer) throws SQLException, IOException{
        String query = "INSERT INTO customers (name) VALUES (?)";
        connect(query, newCustomer, preparedStatement -> {
            Customer customer = (Customer) newCustomer;
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();
        });
    }

    private void createOrder(DaoTransferObject newOrder) throws SQLException, IOException{
        String query = "INSERT INTO orders (customerId, date) VALUES (?, ?)";
        connect(query, newOrder, preparedStatement -> {
            Order order = (Order) newOrder;
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setTimestamp(2, order.getTimeStamp());
            preparedStatement.executeUpdate();
        });
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
