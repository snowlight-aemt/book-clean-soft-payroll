package me.snowlight.employee.transaction;

import me.snowlight.employee.method.MailMethod;
import me.snowlight.employee.model.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    public ChangeMailTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new MailMethod();
    }
}
