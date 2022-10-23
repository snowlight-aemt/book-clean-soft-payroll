package me.snowlight.employee.transaction;

import me.snowlight.employee.method.DirectMethod;
import me.snowlight.employee.model.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    public ChangeDirectTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new DirectMethod();
    }
}
