package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.MonthlyAmountReleasedReport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Order(6)
public class MonthlyAmountReleasedReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        TreeMap<String, MonthlyAmountReleasedReport> monthlyReportMap = new TreeMap<>();

        for (Employee employee : employees) {
            if (employee.getSalaryEvents() != null) {
                for (AmountEvent salaryEvent : employee.getSalaryEvents()) {
                    getDetails(monthlyReportMap, formatter, salaryEvent);
                }
            }
            if (employee.getBonusEvents() != null) {
                for (AmountEvent bonusEvent : employee.getBonusEvents()) {
                    getDetails(monthlyReportMap, formatter, bonusEvent);
                }
            }
            if (employee.getReimbursementEvents() != null) {
                for (AmountEvent reimbursementEvent : employee.getReimbursementEvents()) {
                    getDetails(monthlyReportMap, formatter, reimbursementEvent);
                }
            }
        }

        System.out.println("<===== Monthly Amount Released Report =====>");
        monthlyReportMap.values().forEach(report -> System.out.println("Month: " + report.getMonthYear() + ", Total Amount: " + report.getTotalAmount() + ", Total Employees: " + report.getTotalEmployees()));
    }

    private void getDetails(Map<String, MonthlyAmountReleasedReport> monthlyReportMap, DateTimeFormatter formatter, AmountEvent amountEvent) {
        LocalDate eventDate = amountEvent.getEventDate();
        String monthYearKey = eventDate.format(formatter);

        MonthlyAmountReleasedReport report = monthlyReportMap.computeIfAbsent(monthYearKey, k -> new MonthlyAmountReleasedReport(k, 0, 0));
        report.setTotalAmount(report.getTotalAmount() + amountEvent.getAmount());
        report.setTotalEmployees(report.getTotalEmployees() + 1);
    }
}
