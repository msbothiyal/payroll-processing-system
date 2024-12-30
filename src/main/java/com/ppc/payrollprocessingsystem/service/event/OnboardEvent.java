package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.DateEvent;
import com.ppc.payrollprocessingsystem.model.Employee;

public class OnboardEvent implements Event {
    private final DateEvent onboardEvent;

    public OnboardEvent(DateEvent onboardEvent) {
        this.onboardEvent = onboardEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setOnboardEvent(onboardEvent);
    }
}
