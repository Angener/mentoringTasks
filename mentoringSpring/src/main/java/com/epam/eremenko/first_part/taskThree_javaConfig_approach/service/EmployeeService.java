package com.epam.eremenko.first_part.taskThree_javaConfig_approach.service;

import com.epam.eremenko.first_part.taskThree_javaConfig_approach.Config;
import com.epam.eremenko.first_part.taskThree_javaConfig_approach.entity.Employee;
import com.epam.eremenko.first_part.taskThree_javaConfig_approach.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class EmployeeService {
    private PositionService positionService;
    private static final ApplicationContext CONTEXT =
            new AnnotationConfigApplicationContext(Config.class);
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final Map<String, Employee> employees = new HashMap<>();

    public void hire(String name, Position position) {
        employees.put(name, nameEmployee(name, getEmployee(position)));
        LOGGER.info("Hired " + employees.get(name));
    }

    private Employee nameEmployee(String name, Employee employee) {
        employee.setName(name);
        return employee;
    }

    @NonNull
    private Employee getEmployee(Position position) {
        Employee employee;
        switch (position.getName()) {
            case ("Founder") -> employee = (Employee) CONTEXT.getBean("founderWorker");
            case ("Cook") -> employee = (Employee) CONTEXT.getBean("cookWorker");
            case ("Administrator") -> employee = (Employee) CONTEXT.getBean("administratorWorker");
            case ("Super waiter") -> employee = (Employee) CONTEXT.getBean("superWaiterWorker");
            default -> employee = (Employee) CONTEXT.getBean("waiterWorker");
        }
        return employee;
    }

    public void read() {
        LOGGER.info("Exist employees: ");
        employees.forEach((k, v) -> LOGGER.info(String.valueOf(v)));
    }

    public void fire(@NonNull String name) {
        if (employees.containsKey(name)) {
            LOGGER.info("Fired " + employees.remove(name));
        }
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }
}
