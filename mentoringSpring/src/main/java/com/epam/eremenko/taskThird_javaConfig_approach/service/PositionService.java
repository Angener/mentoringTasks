package com.epam.eremenko.taskThird_javaConfig_approach.service;

import com.epam.eremenko.taskThird_javaConfig_approach.entity.Employee;
import com.epam.eremenko.taskThird_javaConfig_approach.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PositionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);
    private EmployeeService employeeService;
    private final Set<Position> positions = new HashSet<>();

    public void create(@NonNull Position position) {
        positions.add(position);
        LOGGER.info("Created " + position);
    }

    public void read() {
        LOGGER.info("Exist positions");
        positions.forEach(position -> LOGGER.info(String.valueOf(position)));
    }

    public void update(@NonNull Position exist, @NonNull Position update) {
        LOGGER.info(exist + " is changing to " + update);
        clear(exist);
        create(update);
        change(exist, update);
    }

    private void change(@NonNull Position exist, @NonNull Position update) {
        Collection<Employee> employees = employeeService.getEmployees().values();
        employees.stream()
                .filter(employee -> employee.getPosition().equals(exist))
                .peek(employee -> employee.setPosition(update))
                .forEach(employee -> LOGGER.info(employee.getName() + " changed position."));
    }

    private void clear(@NonNull Position position){
        positions.remove(position);
        LOGGER.info("Deleted " + position);
    }

    public void delete(@NonNull Position position) {
        clear(position);
        fireAllPositionEmployees(position);
    }

    private void fireAllPositionEmployees(@NonNull Position position){
        Collection<Employee> employees = employeeService.getEmployees().values();
        employees.stream()
                .filter(employee -> employee.getPosition().equals(position))
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(employeeService::fire);
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Set<Position> getPositions(){
        return positions;
    }
}
