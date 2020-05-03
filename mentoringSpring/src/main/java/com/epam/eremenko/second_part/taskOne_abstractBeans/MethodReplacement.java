package com.epam.eremenko.second_part.taskOne_abstractBeans;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class MethodReplacement implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        return "Hello! You have replaced method in object of class"+ o.getClass();
    }
}
