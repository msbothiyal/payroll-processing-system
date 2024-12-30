package com.ppc.payrollprocessingsystem.service.event;

import com.ppc.payrollprocessingsystem.model.DateEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventFactoryTest {

    @InjectMocks
    private EventFactory eventFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent_Onboard() {
        DateEvent event = new DateEvent();
        Event result = eventFactory.createEvent("ONBOARD", event);
        assertTrue(result instanceof OnboardEvent);
    }

    @Test
    public void testCreateEvent_InvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
            eventFactory.createEvent("INVALID", new Object());
        });
    }
}