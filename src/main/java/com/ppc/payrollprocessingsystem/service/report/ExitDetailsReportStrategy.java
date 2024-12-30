package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.ExitDetails;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Order(3)
public class ExitDetailsReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        TreeMap<String, List<ExitDetails>> monthWiseEmployees = new TreeMap<>();

        for (Employee employee : employees) {
            if (employee.getExitEvent() != null && employee.getExitEvent().getValueDate() != null) {
                LocalDate joinDate = employee.getExitEvent().getValueDate();
                String monthYearKey = joinDate.format(formatter);

                ExitDetails empDetails = new ExitDetails(
                        employee.getFirstName(),
                        employee.getLastName()
                );

                monthWiseEmployees
                        .computeIfAbsent(monthYearKey, k -> new ArrayList<>())
                        .add(empDetails);
            }
        }

        System.out.println("<===== Monthly Exit Details =====>");
        monthWiseEmployees.forEach((monthYear, employeesList) -> {
            System.out.println("Month: " + monthYear);
            employeesList.forEach(employee -> System.out.println("Employee Name: " + employee.getFirstName() + " " + employee.getLastName()));
        });
    }
}
