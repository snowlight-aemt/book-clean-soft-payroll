package me.snowlight.employee.transaction;

import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected void change(Employee employee) {
        employee.setPaymentMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();
}
