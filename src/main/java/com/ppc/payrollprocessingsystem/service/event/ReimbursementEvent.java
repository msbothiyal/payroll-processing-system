package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;

import java.util.ArrayList;

public class ReimbursementEvent implements Event {
    private final AmountEvent amountEvent;

    public ReimbursementEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getReimbursementEvents() == null) {
            employee.setReimbursementEvents(new ArrayList<>());
        }
        employee.getReimbursementEvents().add(amountEvent);
    }
}
