package com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service;

import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Position;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Salary;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PositionServiceTest {

    private final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("TaskTwo.xml");
    private final Logger LOGGER = Logger.getLogger(PositionService.class);
    private PositionService positionService;
    private EmployeeService employeeService;
    private Position updatedPosition;
    private Position actualPosition;

    @Mock
    private Appender mockedAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LOGGER.addAppender(mockedAppender);
        LOGGER.setLevel(Level.INFO);
        positionService = CONTEXT.getBean(PositionService.class);
        employeeService = CONTEXT.getBean(EmployeeService.class);
        updatedPosition = CONTEXT.getBean(Position.class);
        Salary waiterSalary = CONTEXT.getBean(Salary.class);
        waiterSalary.setName("Waiter");
        waiterSalary.setSalary(1865400);
        updatedPosition.setName("Waiter");
        updatedPosition.setSalary(waiterSalary);
        actualPosition = CONTEXT.getBean(Position.class);
        Salary superWaiterSalary = CONTEXT.getBean(Salary.class);
        superWaiterSalary.setName("Super waiter");
        superWaiterSalary.setSalary(201800);
        actualPosition.setName("Super waiter");
        actualPosition.setSalary(superWaiterSalary);
        positionService.create(actualPosition);
    }

    @Test
    public void checkCreateMethod() {
        assertTrue(positionService.getPositions().contains(actualPosition));
    }

    @Test
    public void checkCreateMethodLogging() {
        verify(mockedAppender, times(1))
                .doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().get(0);
        assertEquals("Created " + actualPosition, loggingEvent.getMessage());
    }

    @Test
    public void checkReadMethod() {
        positionService.read();
        verify(mockedAppender, times(3))
                .doAppend(loggingEventCaptor.capture());
        assertEquals(actualPosition.toString(),
                loggingEventCaptor.getAllValues().get(2).getMessage());
        assertEquals(Level.INFO, loggingEventCaptor.getAllValues().get(2).getLevel());
    }

    @Test
    public void checkUpdateMethodLogging() {
        employeeService.hire("Tolik", updatedPosition);
        positionService.update(updatedPosition, actualPosition);
        verify(mockedAppender, times(5))
                .doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().get(1);
        assertEquals(updatedPosition + " is changing to " + actualPosition,
                loggingEvent.getMessage());
        assertEquals(Level.INFO, loggingEvent.getLevel());
    }

    @Test
    public void checkUpdateMethodClearsUpdatedPosition() {
        positionService.update(updatedPosition, actualPosition);
        assertFalse(positionService.getPositions().contains(updatedPosition));
    }

    @Test
    public void checkUpdateMethodCreatesNewPosition() {
        positionService.update(updatedPosition, actualPosition);
        assertTrue(positionService.getPositions().contains(actualPosition));
    }

    @Test
    public void checkUpdateMethodChangesPreviewEmployeePosition() {
        employeeService.hire("Tolik", updatedPosition);
        positionService.update(updatedPosition, actualPosition);
        assertEquals(employeeService.getEmployees().get("Tolik").getPosition(), actualPosition);
    }

    @Test
    public void checkDeleteMethodClearsPosition() {
        positionService.delete(actualPosition);
        assertFalse(positionService.getPositions().contains(actualPosition));
    }

    @Test
    public void checkDeleteMethodFiresAllPositionEmployees() {
        employeeService.hire("Tolik", actualPosition);
        positionService.delete(actualPosition);
        assertFalse(employeeService.getEmployees().containsKey("Tolik"));
    }
}
