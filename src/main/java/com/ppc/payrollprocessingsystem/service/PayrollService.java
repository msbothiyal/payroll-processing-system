package com.ppc.payrollprocessingsystem.service;

import com.ppc.payrollprocessingsystem.exception.InvalidDataFormatException;
import com.ppc.payrollprocessingsystem.exception.MissingDataException;
import com.ppc.payrollprocessingsystem.model.AmountEvent;
import com.ppc.payrollprocessingsystem.model.DateEvent;
import com.ppc.payrollprocessingsystem.model.Employee;
import com.ppc.payrollprocessingsystem.service.event.Event;
import com.ppc.payrollprocessingsystem.service.event.EventFactory;
import com.ppc.payrollprocessingsystem.service.report.ReportStrategy;
import com.ppc.payrollprocessingsystem.util.DateParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private List<ReportStrategy> reportStrategies;

    @Autowired
    private EventFactory eventFactory;

    public void processFile(InputStream inputStream) throws IOException {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<Employee> employees = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    validateEvent(parts[5], "ONBOARD");
                    Employee employee = parseEmployee(parts);
                    employees.add(employee);
                    DateEvent onboardEvent = parseDateEvent(parts);
                    Event event = eventFactory.createEvent("ONBOARD", onboardEvent);
                    event.process(employee);
                } else if (parts.length == 6) {
                    String empId = validateString(parts[1], "Employee ID");
                    String eventType = validateEvent(parts[2], null);
                    Employee employee = findEmployeeById(employees, empId);
                    if (employee != null) {
                        if (eventType.equals("EXIT")) {
                            DateEvent dateEvent = parseDateEvent(parts);
                            Event event = eventFactory.createEvent(eventType, dateEvent);
                            event.process(employee);
                        } else {
                            AmountEvent amountEvent = parseAmountEvent(parts);
                            Event event = eventFactory.createEvent(eventType, amountEvent);
                            event.process(employee);
                        }
                    }
                } else {
                    throw new InvalidDataFormatException("Invalid number of columns in the file");
                }
            }
            generateReports(employees);
        } catch (IOException | InvalidDataFormatException | MissingDataException | DateTimeParseException | NumberFormatException e) {
            System.err.println("Error processing line: " + line);
            System.err.println("Reason: " + e.getMessage());
            throw e;
        }
    }

    private Employee parseEmployee(String[] parts) {
        Employee employee = new Employee();
        employee.setEmpId(validateString(parts[1], "Employee ID"));
        employee.setFirstName(validateString(parts[2], "First Name"));
        employee.setLastName(validateString(parts[3], "Last Name"));
        employee.setDesignation(validateString(parts[4], "Designation"));
        return employee;
    }

    private AmountEvent parseAmountEvent(String[] parts) {
        AmountEvent amountEvent = new AmountEvent();
        amountEvent.setAmount(validateDouble(parts[3]));
        amountEvent.setEventDate(DateParserUtil.parseEventDate(parts[4].trim()));
        amountEvent.setNote(validateString(parts[5], "Notes"));
        return amountEvent;
    }

    private DateEvent parseDateEvent(String[] parts) {
        DateEvent dateEvent = new DateEvent();
        dateEvent.setValueDate(DateParserUtil.parseValueDate(parts[parts.length - 3].trim()));
        dateEvent.setEventDate(DateParserUtil.parseEventDate(parts[parts.length - 2].trim()));
        dateEvent.setNote(validateString(parts[parts.length - 1], "Notes"));
        return dateEvent;
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new MissingDataException(fieldName + " is missing or empty");
        }
        return value.trim();
    }

    private Double validateDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new InvalidDataFormatException("Amount" + " is invalid: " + value);
        }
    }

    private String validateEvent(String value, String fieldName) {
        value = value.trim();
        if (value == null || value.isEmpty()) {
            throw new MissingDataException(value + " is missing or empty");
        }
        if (fieldName == null) {
            if (value.equals("SALARY") || value.equals("BONUS") || value.equals("REIMBURSEMENT") || value.equals("EXIT")) {
                return value;
            } else {
                throw new InvalidDataFormatException("Invalid event type: " + value);
            }
        } else {
            if (value.equals("ONBOARD")) {
                return value;
            } else {
                throw new InvalidDataFormatException("Invalid event type: " + value);
            }
        }
    }

    private Employee findEmployeeById(List<Employee> employees, String empId) {
        return employees.stream()
                .filter(employee -> employee.getEmpId().equals(empId))
                .findFirst()
                .orElseThrow(() -> new MissingDataException("Employee not found: " + empId));
    }

    private void generateReports(List<Employee> employees) {
        reportStrategies.forEach(reportStrategy -> reportStrategy.generateReport(employees));
    }
}
