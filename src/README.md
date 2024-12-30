# Payroll Processing System

## Overview

The Payroll Processing System is designed to handle the upload and processing of payroll data files. It parses the uploaded files, processes employee events, and generates various reports based on the data.

## Components

### Controller Layer

- **PayrollController**: Handles HTTP POST requests to upload multiple files. It processes each file through the `PayrollService` and returns a response indicating the success or failure of each file upload.

### Service Layer

- **PayrollService**: Core service responsible for processing the uploaded files. It reads the file content, parses the data into `Employee` objects, and delegates event processing to the `EventFactory`. After processing the data, it generates various reports using the provided strategies.
- **EventFactory**: Creates event objects based on the event type. It follows the Factory pattern to encapsulate the instantiation logic of different event types (`ONBOARD`, `SALARY`, `BONUS`, `REIMBURSEMENT`, `EXIT`).
- **DateParserUtil**: Utility class to parse different date formats for event and value dates. It handles multiple date formats to ensure flexibility in input data.

### Model Layer

#### Entities

- **Employee**: Represents an employee with their personal and event-related data.
- **AmountEvent** and **DateEvent**: Represent financial and date-related events.

#### Reports

- `ReportStrategy` Interface: Defines a method for generating reports.
- Concrete Report Strategy Classes: Each implements a specific type of report (e.g., `EmployeeFinancialReportStrategy`, `ExitDetailsReportStrategy`, etc.).
- Reports are generated at the end of file processing and printed to the console.

### Error Handling

- **Custom Exceptions**:
    - **InvalidDataFormatException**: Thrown when data format is invalid.
    - **MissingDataException**: Thrown when required data is missing.

### Persistence
- The application operates on in-memory data structures for simplicity, but it can be extended to use databases for persistence.

### Output
- The application generates various reports based on the processed data. The reports are printed to the console.

## Assumptions

- The input files either CSV or TXT are structured and contain a set number of columns for each event type.
- Each line in the input file corresponds to either an employee's `ONBOARD` event, `EXIT` event, or a financial event (`SALARY`, `BONUS`, `REIMBURSEMENT`).
- The line with `ONBOARD` event contains 9 columns, `EXIT` event and financial events contain 6 columns.
- Every employee must have an `ONBOARD` event. The application will throw an exception if an employee does not have an `ONBOARD` event.
- Financial events can occur multiple times for an employee.
- The application supports multiple date formats for event and value dates. It strictly adheres to `DD-MM-YYYY` for event dates and `MM-DD-YYYY` for value dates like onboard date or exit date. 
- The application primarily focuses on generating reports for analysis purposes, hence the console output of reports. It can be extended to generate reports in different formats or store them in databases/files as needed.
- The application generates reports per file upload.

## How to Run the Application

1. Ensure you have Java 23 installed on your machine.
2. Clone the repository and navigate to the project directory.
3. Build and Run the Spring Boot application using your IDE or the following commands:
   ```sh
   # to build and run the application
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
4. The application will start on `http://localhost:8080`.
5. Use the following endpoint to upload files:
   - `POST /api/upload/files`
   - You can use a tool like `curl` or Postman to upload files.
   - The uploaded files should be in CSV or TXT format.
   - The response will indicate the success or failure of each file upload.
6. Output reports will be displayed in the console.

## Example Request

```sh
curl -X POST http://localhost:8080/api/upload/files -F "files=@file1.csv"
```