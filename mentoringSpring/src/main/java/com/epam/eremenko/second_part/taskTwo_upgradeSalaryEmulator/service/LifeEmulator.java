package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.service;

import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifeEmulator {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LifeEmulator.class);
    private EmployeeService employeeService;
    private PositionService positionService;
    private SalaryService salaryService;
    private Position founder;
    private Position cook;
    private Position administrator;
    private Position waiter;
    private Position superWaiter;

    public void emulate() {
        LOGGER.info("Hello! This is the output from" +
                " the task that used only xml config.");
        initializePositions();
        firstQuarter();
        employeeService.read();
        secondQuarter();
        thirdQuarter();
        fourthQuarter();
        positionService.read();
        employeeService.read();
        salaryService.getSalaries().forEach(s -> LOGGER.info(String.valueOf(s)));
    }

    private void initializePositions() {
        positionService.create(founder);
        positionService.create(cook);
        positionService.create(administrator);
        positionService.create(waiter);
    }

    private void firstQuarter() {
        employeeService.hire("Vaska", founder);
        employeeService.hire("Leshka", founder);
        employeeService.hire("froska", waiter);
        employeeService.hire("Jemmy", administrator);
        employeeService.hire("Pashka", cook);
        employeeService.hire("Grisha", waiter);
        employeeService.hire("Stasik", waiter);
    }

    private void secondQuarter() {
        employeeService.fire("Stasik");
        employeeService.hire("Tolik", waiter);
        salaryService.changeSalary(founder, 2.7);
    }

    private void thirdQuarter() {
        employeeService.fire("Jemmy");
        employeeService.hire("Alenka", administrator);
        employeeService.hire("Valusha", administrator);
        positionService.update(waiter, superWaiter);
    }

    private void fourthQuarter() {
        salaryService.changeSalary(administrator, 2);
        positionService.delete(founder);
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    public void setFounder(Position founder) {
        this.founder = founder;
    }

    public void setCook(Position cook) {
        this.cook = cook;
    }

    public void setAdministrator(Position administrator) {
        this.administrator = administrator;
    }

    public void setWaiter(Position waiter) {
        this.waiter = waiter;
    }

    public void setSuperWaiter(Position superWaiter) {
        this.superWaiter = superWaiter;
    }
}
