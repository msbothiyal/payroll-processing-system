package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.MonthlySalaryReport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeMap;

@Service
@Order(4)
public class MonthlySalaryReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        TreeMap<String, MonthlySalaryReport> monthlyReportMap = new TreeMap<>();

        for (Employee employee : employees) {
            if (employee.getSalaryEvents() != null) {
                for (AmountEvent salaryEvent : employee.getSalaryEvents()) {
                    LocalDate salaryDate = salaryEvent.getEventDate();
                    String monthYearKey = salaryDate.format(formatter);

                    MonthlySalaryReport report = monthlyReportMap.computeIfAbsent(monthYearKey, k -> new MonthlySalaryReport(k, 0, 0));
                    report.setTotalSalary(report.getTotalSalary() + salaryEvent.getAmount());
                    report.setTotalEmployees(report.getTotalEmployees() + 1);
                }
            }
        }

        // Print or store the report
        System.out.println("<===== Monthly Salary Report =====>");
        monthlyReportMap.values().forEach(report -> System.out.println("Month: " + report.getMonthYear() + ", Total Salary: " + report.getTotalSalary() + ", Total Employees: " + report.getTotalEmployees()));
    }
}
