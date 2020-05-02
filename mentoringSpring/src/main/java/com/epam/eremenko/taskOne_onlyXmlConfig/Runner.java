package com.epam.eremenko.taskOne_onlyXmlConfig;


import com.epam.eremenko.taskOne_onlyXmlConfig.service.LifeEmulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskOne.xml");
        LifeEmulator lifeEmulator = (LifeEmulator) context.getBean("lifeEmulator");
        lifeEmulator.emulate();
    }
}