package me.snowlight.employee.transaction;

import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    public ChangeClassificationTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected void change(Employee employee) {
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}
