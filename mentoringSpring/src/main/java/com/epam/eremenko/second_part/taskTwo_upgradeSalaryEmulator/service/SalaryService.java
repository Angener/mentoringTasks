package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.service;

import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity.Position;
import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity.Salary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.Set;

public class SalaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryService.class);
    private static final double BASE_SALARY = 10000.00;
    private Set<Salary> salaries;

    public void changeSalary(@NonNull Position position, @NonNull double coefficient) {
        if (coefficient > 0) {
            position.getSalary().setSalary(position.getSalary().getSalary() * coefficient);
            LOGGER.info("Salary was corrected for " + position + " Coefficient " + coefficient);
        }
    }

    public double getBaseSalary() {
        return BASE_SALARY;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }
}
