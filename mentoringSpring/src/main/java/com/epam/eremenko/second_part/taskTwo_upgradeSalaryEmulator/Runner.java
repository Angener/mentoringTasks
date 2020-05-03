package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator;

import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.service.LifeEmulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskFiveServices.xml");
        LifeEmulator lifeEmulator = (LifeEmulator) context.getBean("lifeEmulator");
        lifeEmulator.emulate();
    }
}
