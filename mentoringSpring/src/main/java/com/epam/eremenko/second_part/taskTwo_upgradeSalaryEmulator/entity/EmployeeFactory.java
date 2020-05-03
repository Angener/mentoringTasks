package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity;

import org.springframework.beans.factory.FactoryBean;

public class EmployeeFactory implements FactoryBean<Employee> {

    @Override
    public Employee getObject() throws Exception {
        return new Employee();
    }

    @Override
    public Class<?> getObjectType() {
        return Employee.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
