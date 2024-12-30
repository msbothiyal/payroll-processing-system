package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.DateEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OnboardDetailsReportStrategyTest {

    private OnboardDetailsReportStrategy reportStrategy;

    @BeforeEach
    void setUp() {
        reportStrategy = new OnboardDetailsReportStrategy();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmpId("E001");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDesignation("Developer");
        employee.setOnboardEvent(new DateEvent(LocalDate.now(), LocalDate.now(), "Onboard"));
        employees.add(employee);

        assertDoesNotThrow(() -> reportStrategy.generateReport(employees));
    }
}