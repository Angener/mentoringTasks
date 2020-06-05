package com.eremenko.task_two.dao;


import com.eremenko.task_two.entity.BeanFactory;
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
            handleException(ex);
        }
    }

    private void madeTables() throws SQLException, IOException {
        String customerTable = """
                CREATE TABLE IF NOT EXISTS customers (
                id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                name VARCHAR(40) NOT NULL)
                """;
        String orderTable = """
                CREATE TABLE IF NOT EXISTS orders (
                id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                customerId INT NOT NULL,
                date DATETIME,
                FOREIGN KEY (customerId) REFERENCES customers (id) ON DELETE CASCADE)
                """;
        connect(customerTable, (preparedStatement) -> {
            preparedStatement.addBatch();
            preparedStatement.addBatch(orderTable);
            preparedStatement.executeBatch();
        });
    }

    private void connect(String query, Applier app) throws
            SQLException, IOException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            app.apply(preparedStatement);
        }
    }

    private Connection getConnection() throws SQLException, IOException {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/jdbc.properties")) {
            prop.load(in);
        }
        return DriverManager.getConnection(prop.getProperty("jdbc.url"), prop.getProperty("jdbc.username"),
                prop.getProperty("jdbc.password"));
    }

    private void handleException(Exception ex) throws DataBaseAccessorException {
        LOGGER.debug(ex.getMessage());
        throw new DataBaseAccessorException(ex.getMessage());
    }

    @Override
    public void create(final DaoTransferObject dto) throws DataBaseAccessorException {
        try {
            switchMethod(dto);
        } catch (SQLException | IOException ex) {
            handleException(ex);
        }
    }

    private void switchMethod(final DaoTransferObject dto) throws
            SQLException, IOException, DataBaseAccessorException {
        switch (dto.getType()) {
            case ("customers") -> createCustomer(dto);
            case ("orders") -> createOrder(dto);
            default -> throw new DataBaseAccessorException("Unknown type");
        }
    }

    private void createCustomer(final DaoTransferObject newCustomer) throws SQLException, IOException {
        String query = "INSERT INTO customers (name) VALUES (?)";
        connect(query, preparedStatement -> {
            Customer customer = (Customer) newCustomer;
            preparedStatement.setString(1, customer.getName());
            LOGGER.debug( "Rows created: " +
                    preparedStatement.executeUpdate());
        });
    }

    private void createOrder(final DaoTransferObject newOrder) throws SQLException, IOException {
        String query = "INSERT INTO orders (customerId, date) VALUES (?, ?)";
        connect(query, preparedStatement -> {
            Order order = (Order) newOrder;
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setTimestamp(2, order.getTimeStamp());
            LOGGER.debug( "Rows created: " +
                    preparedStatement.executeUpdate());
        });
    }

    @Override
    public DaoTransferObject getOrder(final int orderId) throws DataBaseAccessorException {
        final Order order = BeanFactory.getOrder();
        try {
            findOrder(orderId, order);
        } catch (IOException | SQLException ex) {
            handleException(ex);
        }
        return order;
    }

    private void findOrder(final int orderId, final Order order) throws SQLException, IOException {
        String query = "SELECT * FROM orders WHERE id = ?";
        connect(query, preparedStatement -> {
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setCustomerId(resultSet.getInt(2));
                order.setTimeStamp(resultSet.getTimestamp(3));
            }
        });
    }

    @Override
    public DaoTransferObject getCustomer(final int customerId) throws DataBaseAccessorException {
        final Customer customer = BeanFactory.getCustomer();
        try {
            findCustomer(customerId, customer);
        } catch (IOException | SQLException ex) {
            handleException(ex);
        }
        return customer;
    }

    private void findCustomer(final int customerId, final Customer customer) throws SQLException, IOException {
        String query = "SELECT * FROM customers WHERE id = ?";
        connect(query, preparedStatement -> {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
            }
        });
    }

    @Override
    public void update(final int id, final DaoTransferObject dto) throws DataBaseAccessorException {
        try {
            updateCustomer(id, dto);
        } catch (IOException | SQLException ex) {
            handleException(ex);
        }
    }

    private void updateCustomer(int id, DaoTransferObject dto) throws SQLException, IOException {
        String query = "UPDATE customers SET name = ? WHERE id = ?" ;
        Customer customer = (Customer) dto;
        connect(query, preparedStatement -> {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, id);
            LOGGER.debug("rows updated: " +
                    preparedStatement.executeUpdate());
        });
    }

    @Override
    public void delete(DaoTransferObject dto) throws DataBaseAccessorException {
        try {
            deleteRow(dto);
        } catch (SQLException | IOException ex) {
            handleException(ex);
        }
    }

    private void deleteRow(DaoTransferObject dto) throws SQLException, IOException {
        String query = "DELETE FROM " + dto.getType() + " WHERE id = ?";
        connect(query, preparedStatement -> {
            preparedStatement.setInt(1, dto.getId());
            LOGGER.debug("Rows deleted: " +
                    preparedStatement.executeUpdate());
        });
    }
}
