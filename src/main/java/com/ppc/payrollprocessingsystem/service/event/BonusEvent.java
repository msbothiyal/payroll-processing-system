package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.Employee;

import java.util.ArrayList;

public class BonusEvent implements Event {
    private final AmountEvent amountEvent;

    public BonusEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getBonusEvents() == null) {
            employee.setBonusEvents(new ArrayList<>());
        }
        employee.getBonusEvents().add(amountEvent);
    }
}