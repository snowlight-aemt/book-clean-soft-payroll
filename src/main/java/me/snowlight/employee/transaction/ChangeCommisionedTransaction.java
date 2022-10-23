package me.snowlight.employee.transaction;

import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.BiweeklySchedule;

public class ChangeCommisionedTransaction extends ChangeClassificationTransaction {
    private float salary;
    private float commissionRate;

    public ChangeCommisionedTransaction(Long empId, float salary, float commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(this.salary, this.commissionRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
