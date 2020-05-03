package com.epam.eremenko.first_part.taskThree_javaConfig_approach.service;

import com.epam.eremenko.first_part.taskThree_javaConfig_approach.Config;
import com.epam.eremenko.first_part.taskThree_javaConfig_approach.entity.Position;
import com.epam.eremenko.first_part.taskThree_javaConfig_approach.entity.Salary;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SalaryServiceTest {
    ApplicationContext context =
            new AnnotationConfigApplicationContext(Config.class);
    private final Logger LOGGER = Logger.getLogger(SalaryService.class);
    SalaryService salaryService;
    Position position;

    @Mock
    private Appender mockedAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        LOGGER.addAppender(mockedAppender);
        LOGGER.setLevel(Level.INFO);
        salaryService = (SalaryService) context.getBean("salaryService");
        position = (Position) context.getBean("cook");
    }

    @Test
    public void changeSalary() {
        double coefficient = 10;
        double salary = position.getSalary().getSalary();
        salaryService.changeSalary(position, coefficient);
        assertEquals(salary * coefficient, position.getSalary().getSalary());
    }

    @Test
    @SuppressWarnings("all")
    public void changeSalaryThrowsNPE() {
        assertThrows(NullPointerException.class, () ->
                salaryService.changeSalary(null, 10));
    }

    @Test
    public void changeSalaryWrongCoefficient() {
        Salary salary = (Salary) context.getBean("cookSalary");
        double oldSalary = position.getSalary().getSalary();
        double coefficient = 0;
        salaryService.changeSalary(position, coefficient);
        assertEquals(oldSalary, salary.getSalary());
    }

    @Test
    public void changeSalaryLogging() {
        double coefficient = 2;
        salaryService.changeSalary(position, coefficient);
        verify(mockedAppender, times(1))
                .doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().get(0);
        assertEquals("Salary was corrected for " + position +
                        " Coefficient " + coefficient,
                loggingEvent.getMessage());
        assertEquals(Level.INFO, loggingEvent.getLevel());
    }
}
