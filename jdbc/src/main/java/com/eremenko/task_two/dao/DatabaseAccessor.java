package com.eremenko.task_two.dao;


public interface DatabaseAccessor {
    void createTables() throws DataBaseAccessorException;
    void create (DaoTransferObject customer) throws DataBaseAccessorException;
}
