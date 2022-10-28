package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.SalesReceipt;

import java.time.LocalDate;

public class SalesReceiptTransaction implements Transaction {

    private Long empId;
    private LocalDate date;
    private float amount;

    public SalesReceiptTransaction(Long empId, LocalDate date, float amount) {
        this.empId = empId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getEmployee(empId);
        CommissionedClassification commissionedClassification =
                (CommissionedClassification) employee.getPaymentClassification();

        commissionedClassification.setSalesReceipt(new SalesReceipt(date, amount));

    }
}
