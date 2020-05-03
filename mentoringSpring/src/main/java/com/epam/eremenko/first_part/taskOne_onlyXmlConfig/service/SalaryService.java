package com.epam.eremenko.taskOne_onlyXmlConfig.service;

import com.epam.eremenko.taskOne_onlyXmlConfig.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

public class SalaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryService.class);
    private static final double BASE_SALARY = 10000.00;

    public void changeSalary(@NonNull Position position, @NonNull double coefficient) {
        if (coefficient > 0) {
            position.getSalary().setSalary(position.getSalary().getSalary() * coefficient);
            LOGGER.info("Salary was corrected for " + position + " Coefficient " + coefficient);
        }
    }

    public double getBaseSalary() {
        return BASE_SALARY;
    }
}
