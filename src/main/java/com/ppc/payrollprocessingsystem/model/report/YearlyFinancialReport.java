package com.ppc.payrollprocessingsystem.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class YearlyFinancialReport {
    private String event;
    private String empId;
    private LocalDate eventDate;
    private double eventValue;
}
