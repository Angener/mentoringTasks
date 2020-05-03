package com.epam.eremenko.first_part.taskOne_onlyXmlConfig;

import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.entity.Employee;
import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.entity.Position;
import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.entity.Salary;
import com.epam.eremenko.first_part.taskOne_onlyXmlConfig.service.EmployeeService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceTest {

    private final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("TaskOne.xml");
    private final Logger LOGGER = Logger.getLogger(EmployeeService.class);
    private EmployeeService employeeService;
    private final Position founder = (Position) CONTEXT.getBean("founder");
    private final Position cook = (Position) CONTEXT.getBean("cook");
    private final Position administrator = (Position) CONTEXT.getBean("administrator");
    private final Position waiter = (Position) CONTEXT.getBean("waiter");
    private final Position superWaiter = (Position) CONTEXT.getBean("superWaiter");

    @Mock
    private Appender mockedAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = (EmployeeService) CONTEXT.getBean("employeeService");
        LOGGER.addAppender(mockedAppender);
        LOGGER.setLevel(Level.INFO);
        employeeService.hire("Vaska", founder);
        employeeService.hire("Petka", cook);
        employeeService.hire("Tolik", administrator);
        employeeService.hire("Froska", waiter);
        employeeService.hire("Grishka", superWaiter);
    }

    @Test
    public void putEmployeeToMap() {
        assert (employeeService.getEmployees().containsKey("Tolik"));
    }

    @Test
    public void createConfiguredEmployee() {
        Salary salary = (Salary) CONTEXT.getBean("administratorSalary");
        employeeService.hire("Tolik", administrator);
        Employee result = employeeService.getEmployees().get("Tolik");
        assertTrue(result.getPosition().equals(administrator) &&
                result.getPosition().getSalary().equals(salary));
    }

    @Test
    public void switchPositions() {
        List<Position> positionList = Arrays.asList(founder, cook,
                administrator, waiter, superWaiter);
        List<Position> positions = new ArrayList<>(positionList);
        Collection<Employee> employee = employeeService.getEmployees().values();
        employee.stream()
                .map(Employee::getPosition)
                .collect(Collectors.toList())
                .stream()
                .peek(System.out::println)
                .peek(p -> {
                    if (p.getName().equals(cook.getName())) {
                        positions.remove(1);
                    }
                })
                .forEach(positions::remove);
        assertTrue(positions.isEmpty());
    }

    @Test
    public void checkEmployeesHiringLog() {
        String name = "Tolik";
        verify(mockedAppender, times(5))
                .doAppend(loggingEventCaptor.capture());
        LoggingEvent loggingEvent = loggingEventCaptor.getAllValues().get(2);
        assertEquals("Hired " + employeeService.getEmployees().get(name),
                loggingEvent.getMessage());
        assertEquals(Level.INFO, loggingEvent.getLevel());
    }

    @Test
    public void checkHiringTrowsNPE() {
        assertThrows(NullPointerException.class,
                () -> employeeService.hire("Tolichek", null));
    }

    @Test
    public void checkReadMethod() {
        employeeService.read();
        verify(mockedAppender, times(11))
                .doAppend(loggingEventCaptor.capture());
        assertEquals("Employee: Tolik. Position: Administrator. Salary: 32000.0.",
                loggingEventCaptor.getAllValues().get(6).getMessage());
        assertEquals(Level.INFO, loggingEventCaptor.getAllValues().get(9).getLevel());
    }

    @Test
    public void checkFireMethod() {
        employeeService.getEmployees().clear();
        employeeService.hire("Tolik", administrator);
        employeeService.fire("Tolik");
        assertTrue(employeeService.getEmployees().isEmpty());
    }

    @Test
    public void passFireUnavailableEmployee() {
        employeeService.fire("Deniska");
        verify(mockedAppender, times(5))
                .doAppend(loggingEventCaptor.capture());
    }
}
