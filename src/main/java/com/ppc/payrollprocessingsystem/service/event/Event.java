package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.Employee;

public interface Event {
    void process(Employee employee);
}
