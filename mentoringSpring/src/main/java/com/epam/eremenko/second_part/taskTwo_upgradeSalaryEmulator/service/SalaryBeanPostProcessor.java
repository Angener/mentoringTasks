package com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.service;

import com.epam.eremenko.second_part.taskTwo_upgradeSalaryEmulator.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SalaryBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Position){
            if (((Position)bean).getSalary().getSalary() < 25000){
                LOGGER.warn(((Position) bean).getName() + " salary less than 25 000 !");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
