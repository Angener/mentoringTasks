package com.eremenko.task_two.entity;

import com.eremenko.task_two.dao.DaoTransferObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

public class Order implements DaoTransferObject, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String TYPE = "orders";

    private int id;
    private int customerId;
    private Timestamp timeStamp;

    public Order(){
    }

    public Order(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                Objects.equals(timeStamp, order.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, timeStamp);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", timeStamp=" + timeStamp +
                '}';
    }
}