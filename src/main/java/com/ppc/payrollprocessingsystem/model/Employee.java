package com.ppc.payrollprocessingsystem.model;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private String empId;
    private String firstName;
    private String lastName;
    private String designation;
    private DateEvent onboardEvent;
    private List<AmountEvent> salaryEvents;
    private List<AmountEvent> bonusEvents;
    private List<AmountEvent> reimbursementEvents;
    private DateEvent exitEvent;
}
