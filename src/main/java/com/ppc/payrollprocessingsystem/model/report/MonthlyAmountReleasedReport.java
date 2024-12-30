package com.ppc.payrollprocessingsystem.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyAmountReleasedReport {
    private String monthYear;
    private double totalAmount;
    private int totalEmployees;
}
