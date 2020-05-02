package com.epam.eremenko.taskTwo_annotationBased_approach;


import com.epam.eremenko.taskTwo_annotationBased_approach.service.LifeEmulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("TaskTwo.xml");
        LifeEmulator lifeEmulator = context.getBean(LifeEmulator.class);
        lifeEmulator.emulate();
    }
}