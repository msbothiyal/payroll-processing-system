package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;

import java.util.ArrayList;

public class SalaryEvent implements Event {
    private final AmountEvent amountEvent;

    public SalaryEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getSalaryEvents() == null) {
            employee.setSalaryEvents(new ArrayList<>());
        }
        employee.getSalaryEvents().add(amountEvent);
    }
}
