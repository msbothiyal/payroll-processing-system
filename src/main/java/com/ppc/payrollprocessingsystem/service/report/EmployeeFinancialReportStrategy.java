package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.EmployeeFinancialReport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Order(5)
public class EmployeeFinancialReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        System.out.println("<===== Employee Financial Report =====>");
        for (Employee employee : employees) {
            double totalAmountPaid = 0;
            if (employee.getSalaryEvents() != null) {
                totalAmountPaid += employee.getSalaryEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            if (employee.getBonusEvents() != null) {
                totalAmountPaid += employee.getBonusEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            if (employee.getReimbursementEvents() != null) {
                totalAmountPaid += employee.getReimbursementEvents().stream().mapToDouble(AmountEvent::getAmount).sum();
            }
            EmployeeFinancialReport report = new EmployeeFinancialReport(employee.getEmpId(), employee.getFirstName(), employee.getLastName(), totalAmountPaid);
            System.out.println("Employee ID: " + report.getEmpId() + ", Name: " + report.getFirstName() + " " + report.getLastName() + ", Total Amount Paid: " + report.getTotalAmountPaid());
        }
    }
}
