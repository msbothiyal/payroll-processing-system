package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.DateEvent;
import com.ppc.payrollprocessingsystem.model.Employee;

public class ExitEvent implements Event {
    private final DateEvent dateEvent;

    public ExitEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setExitEvent(dateEvent);
    }
}
