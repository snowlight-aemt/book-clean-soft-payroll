package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.TimeCard;

import java.time.LocalDate;

public class TimeCardTrancation  implements Transaction {
    private Long empId;
    private LocalDate date;
    private float hours;

    public TimeCardTrancation(LocalDate date, float hours, Long empId) {
        this.empId = empId;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getEmployee(this.empId);
        if (employee != null) {
            HourlyClassification paymentClassification = (HourlyClassification) employee.getPaymentClassification();
            if (paymentClassification != null) {
                paymentClassification.AddTimeCard(new TimeCard(this.date, this.hours));
            } else {
                System.err.println("Tried to add timecard to non-hourly employee");
            }
        } else {
            System.err.println("No cush employee.");
        }
    }
}
