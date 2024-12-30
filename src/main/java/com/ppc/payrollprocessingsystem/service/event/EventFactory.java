package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.DateEvent;
import org.springframework.stereotype.Service;

@Service
public class EventFactory {

    public Event createEvent(String eventType, Object event) {
        return switch (eventType) {
            case "ONBOARD" -> new OnboardEvent((DateEvent) event);
            case "SALARY" -> new SalaryEvent((AmountEvent) event);
            case "BONUS" -> new BonusEvent((AmountEvent) event);
            case "REIMBURSEMENT" -> new ReimbursementEvent((AmountEvent) event);
            case "EXIT" -> new ExitEvent((DateEvent) event);
            default -> throw new IllegalArgumentException("Invalid event type: " + eventType);
        };
    }
}
