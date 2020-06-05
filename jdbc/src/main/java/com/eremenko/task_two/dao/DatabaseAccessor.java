package com.eremenko.task_two.dao;


public interface DatabaseAccessor {
    void createTables() throws DataBaseAccessorException;
    void create (DaoTransferObject dto) throws DataBaseAccessorException;
    DaoTransferObject getOrder(int orderId) throws DataBaseAccessorException;
    DaoTransferObject getCustomer(int customerId) throws DataBaseAccessorException;
    void update(int id, DaoTransferObject dto) throws DataBaseAccessorException;
    void delete(DaoTransferObject dto) throws DataBaseAccessorException;
}
