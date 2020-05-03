package com.epam.eremenko.first_part;

import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.service.LifeEmulator;
import com.epam.eremenko.first_part.taskThree_javaConfig_approach.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        executeTaskOne();
        executeTaskTwo();
        executeTaskThree();
    }

    private static void executeTaskOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskOne.xml");
        LifeEmulator lifeEmulator = (LifeEmulator) context.getBean("lifeEmulator");
        lifeEmulator.emulate();
    }

    private static void executeTaskTwo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskTwo.xml");
        com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service.LifeEmulator
                lifeEmulator = context.getBean(
                com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service.LifeEmulator.class);
        lifeEmulator.emulate();
    }

    private static void executeTaskThree() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        com.epam.eremenko.first_part.taskThree_javaConfig_approach.service.LifeEmulator
                lifeEmulator = (com.epam.eremenko.first_part.taskThree_javaConfig_approach.service.LifeEmulator)
                ctx.getBean("lifeEmulator");
        lifeEmulator.emulate();
    }
}
