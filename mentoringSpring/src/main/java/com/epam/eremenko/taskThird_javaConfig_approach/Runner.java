package com.epam.eremenko.taskThird_javaConfig_approach;

import com.epam.eremenko.taskThird_javaConfig_approach.service.LifeEmulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskThree.xml");
        LifeEmulator lifeEmulator = (LifeEmulator) context.getBean("lifeEmulator");
        lifeEmulator.emulate();
    }
}
