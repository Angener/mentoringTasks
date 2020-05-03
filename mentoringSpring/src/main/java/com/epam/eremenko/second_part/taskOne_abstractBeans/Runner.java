package com.epam.eremenko.second_part.taskOne_abstractBeans;

import com.epam.eremenko.second_part.taskOne_abstractBeans.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("SecondPartTaskOne.xml");
        Logger logger = LoggerFactory.getLogger(Runner.class);
        logger.info(String.valueOf(context.getBean("a", A.class)));
        logger.info(String.valueOf(context.getBean("b", B.class)));
        logger.info(String.valueOf(context.getBean("c", C.class)));
        logger.info(context.getBean("e", E.class).introduce());
        context.refresh();
        context.getBean("f", F.class);
        context.close();
    }
}
