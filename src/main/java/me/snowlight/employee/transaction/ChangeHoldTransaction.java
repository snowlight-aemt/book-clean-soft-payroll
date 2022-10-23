package me.snowlight.employee.transaction;

import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.method.MailMethod;
import me.snowlight.employee.model.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
