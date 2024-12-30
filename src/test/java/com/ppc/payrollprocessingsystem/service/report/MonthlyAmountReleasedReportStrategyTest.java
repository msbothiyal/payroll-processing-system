package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyAmountReleasedReportStrategyTest {

    private MonthlyAmountReleasedReportStrategy reportStrategy;

    @BeforeEach
    void setUp() {
        reportStrategy = new MonthlyAmountReleasedReportStrategy();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        AmountEvent salaryEvent = new AmountEvent(1000, LocalDate.now(), "Salary");
        employee.setSalaryEvents(List.of(salaryEvent));
        employees.add(employee);

        assertDoesNotThrow(() -> reportStrategy.generateReport(employees));
    }
}