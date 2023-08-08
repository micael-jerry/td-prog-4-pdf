package com.spring.boot.employee.utils;

import com.spring.boot.employee.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
    public void writeEmployeeToCsv(List<Employee> employeeList, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord(
                    "ID", "Personnel Number", "First Name", "Last Name", "Birthday", "Sex",
                    "CNAPS Number", "Children Count", "Socio-Professional Category", "Function",
                    "Start Date", "Departure Date", "Personal Email", "Professional Email"
            );
            for (Employee employee : employeeList) {
                printer.printRecord(
                        employee.getId(),
                        employee.getPersonnelNumber(),
                        employee.getFirstname(),
                        employee.getLastname(),
                        employee.getBirthday(),
                        employee.getSex(),
                        employee.getCnapsNumber(),
                        employee.getChildrenCount(),
                        employee.getSocioProfessionalCategory(),
                        employee.getFunction(),
                        employee.getStartDate(),
                        employee.getDepartureDate(),
                        employee.getPersonalEmail(),
                        employee.getProfessionalEmail()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
