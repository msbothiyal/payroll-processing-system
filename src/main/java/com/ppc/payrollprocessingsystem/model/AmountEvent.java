package com.ppc.payrollprocessingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountEvent {
    private double amount;
    private LocalDate eventDate;
    private String note;
}
