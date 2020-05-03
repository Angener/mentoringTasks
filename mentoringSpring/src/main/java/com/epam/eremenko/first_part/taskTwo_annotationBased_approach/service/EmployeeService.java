package com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service;

import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Employee;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Position;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Component
public class EmployeeService {
    @Required
    private PositionService positionService;
    private static final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("TaskTwo.xml");
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
        Employee employee = CONTEXT.getBean(Employee.class);
        employee.setPosition(position);
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

    @Autowired()
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }
}
