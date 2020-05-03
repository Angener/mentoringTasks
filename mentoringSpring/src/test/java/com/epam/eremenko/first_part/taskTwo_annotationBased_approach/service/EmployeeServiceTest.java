package com.epam.eremenko.first_part.taskTwo_annotationBased_approach;

import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Employee;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Position;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.entity.Salary;
import com.epam.eremenko.first_part.taskTwo_annotationBased_approach.service.EmployeeService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    private final ApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("TaskTwo.xml");
    private final Logger LOGGER = Logger.getLogger(EmployeeService.class);
    private EmployeeService employeeService;
    private Position founder = CONTEXT.getBean(Position.class);
    private Position cook = CONTEXT.getBean(Position.class);
    private Position administrator = CONTEXT.getBean(Position.class);
    private Position waiter = CONTEXT.getBean(Position.class);
    private Position superWaiter = CONTEXT.getBean(Position.class);

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
        Salary founderSalary = CONTEXT.getBean(Salary.class);
        founderSalary.setName("dividend");
        founderSalary.setSalary(5230000);
        Salary cookSalary = CONTEXT.getBean(Salary.class);
        cookSalary.setName("Cook");
        cookSalary.setSalary(2312000);
        Salary administratorSalary = CONTEXT.getBean(Salary.class);
        administratorSalary.setName("Administrator");
        administratorSalary.setSalary(234321);
        Salary waiterSalary = CONTEXT.getBean(Salary.class);
        waiterSalary.setName("Waiter");
        waiterSalary.setSalary(1865400);
        Salary superWaiterSalary = CONTEXT.getBean(Salary.class);
        superWaiterSalary.setName("Super waiter");
        superWaiterSalary.setSalary(201800);

        founder = CONTEXT.getBean(Position.class);
        founder.setName("Founder");
        founder.setSalary(founderSalary);
        cook = CONTEXT.getBean(Position.class);
        cook.setName("Cook");
        cook.setSalary(cookSalary);
        administrator = CONTEXT.getBean(Position.class);
        administrator.setName("Administrator");
        administrator.setSalary(administratorSalary);
        waiter = CONTEXT.getBean(Position.class);
        waiter.setName("Waiter");
        waiter.setSalary(waiterSalary);
        superWaiter = CONTEXT.getBean(Position.class);
        superWaiter.setName("Super waiter");
        superWaiter.setSalary(superWaiterSalary);
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
        Salary salary = CONTEXT.getBean(Salary.class);
        salary.setName("Administrator");
        salary.setSalary(234321);
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
        assertEquals("Employee: Tolik. Position: Administrator. Salary: 234321.0.",
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
