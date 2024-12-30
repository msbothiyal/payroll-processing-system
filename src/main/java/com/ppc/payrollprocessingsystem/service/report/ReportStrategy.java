package com.ppc.payrollprocessingsystem.service.report;

import com.ppc.payrollprocessingsystem.model.Employee;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface ReportStrategy {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    void generateReport(List<Employee> employees);
}