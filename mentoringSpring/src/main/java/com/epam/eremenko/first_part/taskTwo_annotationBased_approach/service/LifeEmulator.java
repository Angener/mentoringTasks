package com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service;

import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Position;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Salary;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LifeEmulator {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LifeEmulator.class);
    private static final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("TaskTwo.xml");
    @Required
    private final EmployeeService employeeService;
    @Required
    private final PositionService positionService;
    @Required
    private final SalaryService salaryService;
    private Position founder;
    private Position cook;
    private Position administrator;
    private Position waiter;
    private Position superWaiter;

    @Autowired
    public LifeEmulator(EmployeeService employeeService,
                        PositionService positionService,
                        SalaryService salaryService
    ) {
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.salaryService = salaryService;
    }

    public void emulate() {
        LOGGER.info("Hello! This is the output from" +
                " the task that used annotation-based config.");
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
        Salary founderSalary = CONTEXT.getBean(Salary.class);
        founderSalary.setName("dividend");
        founderSalary.setSalary(5230000);
        Salary cookSalary = CONTEXT.getBean(Salary.class);
        cookSalary.setName("Cook");
        cookSalary.setSalary(2312000);
        Salary administratorSalary = CONTEXT.getBean(Salary.class);
        administratorSalary.setName("Administrator");
        administratorSalary.setSalary(234321);
        Salary waiterSalary = CONTEXT.getBean(Salary.class);
        waiterSalary.setName("Waiter");
        waiterSalary.setSalary(1865400);
        Salary superWaiterSalary = CONTEXT.getBean(Salary.class);
        superWaiterSalary.setName("Super waiter");
        superWaiterSalary.setSalary(201800);

        founder = CONTEXT.getBean(Position.class);
        founder.setName("Founder");
        founder.setSalary(founderSalary);
        cook = CONTEXT.getBean(Position.class);
        cook.setName("Cook");
        cook.setSalary(cookSalary);
        administrator = CONTEXT.getBean(Position.class);
        administrator.setName("Administrator");
        administrator.setSalary(administratorSalary);
        waiter = CONTEXT.getBean(Position.class);
        waiter.setName("Waiter");
        waiter.setSalary(waiterSalary);
        superWaiter = CONTEXT.getBean(Position.class);
        superWaiter.setName("Super waiter");
        superWaiter.setSalary(superWaiterSalary);

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
        salaryService.changeSalary(founder, 0.9);
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
