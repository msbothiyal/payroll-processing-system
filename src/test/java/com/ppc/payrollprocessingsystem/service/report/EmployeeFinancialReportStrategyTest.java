package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmployeeFinancialReportStrategyTest {

    private EmployeeFinancialReportStrategy reportStrategy;

    @BeforeEach
    void setUp() {
        reportStrategy = new EmployeeFinancialReportStrategy();
    }

    @Test
    void testGenerateReport_withValidEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setEmpId("E001");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setSalaryEvents(List.of(new AmountEvent(1000, LocalDate.now(), "Salary")));
        employee.setBonusEvents(List.of(new AmountEvent(500, LocalDate.now(), "Bonus")));
        employee.setReimbursementEvents(List.of(new AmountEvent(200, LocalDate.now(), "Reimbursement")));
        employees.add(employee);

        assertDoesNotThrow(() -> reportStrategy.generateReport(employees));
    }
}