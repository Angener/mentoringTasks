package com.epam.eremenko.taskThird_javaConfig_approach.service;

import com.epam.eremenko.taskThird_javaConfig_approach.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifeEmulator {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LifeEmulator.class);
    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final SalaryService salaryService;
    private final Position founder;
    private final Position cook;
    private final Position administrator;
    private final Position waiter;
    private final Position superWaiter;

    public LifeEmulator(EmployeeService employeeService,
                        PositionService positionService,
                        SalaryService salaryService,
                        Position founder, Position cook,
                        Position administrator,
                        Position waiter, Position superWaiter) {
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.salaryService = salaryService;
        this.founder = founder;
        this.cook = cook;
        this.administrator = administrator;
        this.waiter = waiter;
        this.superWaiter = superWaiter;
    }

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
}
