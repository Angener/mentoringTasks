package com.eremenko.task_two;

import com.eremenko.task_two.dao.DaoTransferObject;
import com.eremenko.task_two.dao.DataBaseAccessorException;
import com.eremenko.task_two.dao.DatabaseAccessor;
import com.eremenko.task_two.dao.DatabaseAccessorFactory;
import com.eremenko.task_two.entity.BeanFactory;
import com.eremenko.task_two.entity.Order;

public class Runner {
    public static void main(String[] args) throws DataBaseAccessorException {
        DatabaseAccessor dao = DatabaseAccessorFactory.getInstance().getDatabaseAccessor();

        DaoTransferObject order = BeanFactory.createOrder(1);
        dao.create(order);



    }




}
