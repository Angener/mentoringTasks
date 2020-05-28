package com.eremenko.task_one.dao;

public class DataBaseAccessorException extends Exception {
    private static final long serialVersionUID = 1L;

    public DataBaseAccessorException() {
    }

    public DataBaseAccessorException(String message) {
        super(message);
    }

    public DataBaseAccessorException(Exception ex) {
        super(ex);
    }

    public DataBaseAccessorException(String message, Exception ex) {
        super(message, ex);
    }
}
