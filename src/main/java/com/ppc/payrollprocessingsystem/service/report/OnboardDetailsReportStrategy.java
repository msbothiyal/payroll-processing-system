package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.model.report.OnboardDetails;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Order(2)
public class OnboardDetailsReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        TreeMap<String, List<OnboardDetails>> monthWiseEmployees = new TreeMap<>();

        for (Employee employee : employees) {
            if (employee.getOnboardEvent() != null && employee.getOnboardEvent().getValueDate() != null) {
                LocalDate joinDate = employee.getOnboardEvent().getValueDate();
                String monthYearKey = joinDate.format(formatter);

                OnboardDetails empDetails = new OnboardDetails(
                        employee.getEmpId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDesignation()
                );

                monthWiseEmployees
                        .computeIfAbsent(monthYearKey, k -> new ArrayList<>())
                        .add(empDetails);
            }
        }

        System.out.println("<===== Monthly Joining Details =====>");
        monthWiseEmployees.forEach((monthYear, employeesList) -> {
            System.out.println("Month: " + monthYear);
            employeesList.forEach(employee -> System.out.println("Employee ID: " + employee.getEmpId() + ", Name: " + employee.getFirstName() + " " + employee.getLastName() + ", Designation: " + employee.getDesignation()));
        });
    }
}
