package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.DateEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExitDetailsReportStrategyTest {

    private ExitDetailsReportStrategy reportStrategy;

    @BeforeEach
    void setUp() {
        reportStrategy = new ExitDetailsReportStrategy();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setExitEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Exit"));
        employees.add(employee);

        assertDoesNotThrow(() -> reportStrategy.generateReport(employees));
    }
}