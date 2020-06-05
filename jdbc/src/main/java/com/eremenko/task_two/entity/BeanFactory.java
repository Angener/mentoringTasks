package com.eremenko.task_two.entity;

import com.eremenko.task_two.dao.DaoTransferObject;

import java.sql.Timestamp;
import java.util.Calendar;

public final class BeanFactory {

    private BeanFactory(){}

    public static DaoTransferObject createCustomer(String name){
        return new Customer(name);
    }

    public static DaoTransferObject createOrder(int customerId){
        Order order = new Order(customerId);
        order.setTimeStamp(getCurrentTime());
        return order;
    }

    private static Timestamp getCurrentTime() {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static Order getOrder(){
        return new Order();
    }


    public static Customer getCustomer(){
        return new Customer();
    }

}
