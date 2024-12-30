package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.YearlyFinancialReport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;

@Service
@Order(7)
public class YearlyFinancialReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        TreeMap<Year, List<YearlyFinancialReport>> yearlyReportMap = new TreeMap<>();

        for (Employee employee : employees) {
            String empId = employee.getEmpId();

            if (employee.getSalaryEvents() != null) {
                for (AmountEvent event : employee.getSalaryEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "SALARY", empId, event);
                }
            }

            if (employee.getBonusEvents() != null) {
                for (AmountEvent event : employee.getBonusEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "BONUS", empId, event);
                }
            }

            if (employee.getReimbursementEvents() != null) {
                for (AmountEvent event : employee.getReimbursementEvents()) {
                    addEventToYearlyReport(yearlyReportMap, "REIMBURSEMENT", empId, event);
                }
            }
        }

        System.out.println("<===== Yearly Financial Report =====>");
        yearlyReportMap.forEach((year, reports) -> {
            System.out.println("Year: " + year);
            reports.forEach(report -> System.out.println("Event: " + report.getEvent() + ", Employee ID: " + report.getEmpId() + ", Event Date: " + report.getEventDate() + ", Event Value: " + report.getEventValue()));
        });
    }

    private void addEventToYearlyReport(Map<Year, List<YearlyFinancialReport>> yearlyReportMap, String eventType,
                                               String empId, AmountEvent event) {
        Year year = Year.from(event.getEventDate());
        YearlyFinancialReport report = new YearlyFinancialReport(eventType, empId, event.getEventDate(), event.getAmount());

        yearlyReportMap
                .computeIfAbsent(year, k -> new ArrayList<>())
                .add(report);
    }
}
