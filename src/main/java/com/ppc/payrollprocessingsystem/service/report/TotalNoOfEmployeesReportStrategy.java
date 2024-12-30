package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.Employee;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Order(1)
public class TotalNoOfEmployeesReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        System.out.println("<===== Total Number of Employees Report =====>");
        System.out.println("Total number of employees in this organization: " + employees.size());
    }
}
