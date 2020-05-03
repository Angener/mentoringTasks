package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.service;

import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity.Position;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeEmulatorFactory {
    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("TaskFiveServices.xml", "TaskFiveEnt.xml");

    private final static EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
    private final static PositionService positionService = context.getBean("positionService", PositionService.class);
    private final static SalaryService salaryService = context.getBean("salaryService", SalaryService.class);
    private final static Position founder = context.getBean("founder", Position.class);
    private final static Position cook = context.getBean("cook", Position.class);
    private final static Position administrator = context.getBean("administrator", Position.class);
    private final static Position waiter = context.getBean("waiter", Position.class);
    private final static Position superWaiter = context.getBean("superWaiter", Position.class);


    public static LifeEmulator getBean() {
        LifeEmulator lifeEmulator = new LifeEmulator();
        lifeEmulator.setEmployeeService(employeeService);
        lifeEmulator.setPositionService(positionService);
        lifeEmulator.setSalaryService(salaryService);
        lifeEmulator.setFounder(founder);
        lifeEmulator.setCook(cook);
        lifeEmulator.setAdministrator(administrator);
        lifeEmulator.setWaiter(waiter);
        lifeEmulator.setSuperWaiter(superWaiter);
        return lifeEmulator;
    }
}
