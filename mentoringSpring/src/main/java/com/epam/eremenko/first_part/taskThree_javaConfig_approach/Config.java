package com.epam.eremenko.taskThree_javaConfig_approach;

import com.epam.eremenko.taskThree_javaConfig_approach.entity.Employee;
import com.epam.eremenko.taskThree_javaConfig_approach.entity.Position;
import com.epam.eremenko.taskThree_javaConfig_approach.entity.Salary;
import com.epam.eremenko.taskThree_javaConfig_approach.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Bean
    public EmployeeService employeeService() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.setPositionService(positionService());
        return employeeService;
    }

    @Bean
    public PositionService positionService() {
        return new PositionService();
    }

    @Bean
    public SalaryService salaryService() {
        return new SalaryService();
    }

    @Bean
    public LifeEmulator lifeEmulator() {
        return new LifeEmulator(employeeService(),
                positionService(), salaryService(), founder(),
                cook(), administrator(), waiter(),
                superWaiter());
    }

    @Bean
    public Salary founderSalary() {
        Salary salary = new Salary();
        salary.setName("dividend");
        salary.setSalary(salaryService().getBaseSalary() * 123);
        return salary;
    }

    @Bean
    public Salary cookSalary() {
        Salary salary = new Salary();
        salary.setName("Cook");
        salary.setSalary(Math.random() * 10000);
        return salary;
    }

    @Bean
    public Salary administratorSalary() {
        Salary salary = new Salary();
        salary.setName("Administrator");
        salary.setSalary(salaryService().getBaseSalary() * 3.2);
        return salary;
    }

    @Bean
    public Salary waiterSalary() {
        Salary salary = new Salary();
        salary.setName("Waiter");
        salary.setSalary(salaryService().getBaseSalary() * 1.2);
        return salary;
    }

    @Bean
    public Salary superWaiterSalary() {
        Salary salary = new Salary();
        salary.setName("Super waiter");
        salary.setSalary(salaryService().getBaseSalary() * 2.2);
        return salary;
    }

    @Bean
    public Position founder() {
        return new Position("Founder", founderSalary());
    }

    @Bean
    public Position cook() {
        return new Position("Cook", cookSalary());
    }

    @Bean
    public Position administrator() {
        return new Position("Administrator", administratorSalary());
    }

    @Bean
    public Position waiter() {
        return new Position("Waiter", waiterSalary());
    }

    @Bean
    public Position superWaiter() {
        return new Position("Super waiter", superWaiterSalary());
    }

    @Bean
    @Scope("prototype")
    public Employee founderWorker() {
        Employee employee = new Employee();
        employee.setPosition(founder());
        return employee;
    }

    @Bean
    @Scope("prototype")
    public Employee cookWorker() {
        Employee employee = new Employee();
        employee.setPosition(cook());
        return employee;
    }

    @Bean
    @Scope("prototype")
    public Employee administratorWorker() {
        Employee employee = new Employee();
        employee.setPosition(administrator());
        return employee;
    }

    @Bean
    @Scope("prototype")
    public Employee waiterWorker() {
        Employee employee = new Employee();
        employee.setPosition(waiter());
        return employee;
    }

    @Bean
    @Scope("prototype")
    public Employee superWaiterWorker() {
        Employee employee = new Employee();
        employee.setPosition(superWaiter());
        return employee;
    }
}
