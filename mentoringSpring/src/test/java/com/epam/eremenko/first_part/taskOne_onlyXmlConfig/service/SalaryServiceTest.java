package com.epam.eremenko.first_part.taskOne_onlyXmlConfig;

import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.entity.Position;
import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.entity.Salary;
import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.service.SalaryService;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SalaryServiceTest {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("TaskOne.xml");
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
