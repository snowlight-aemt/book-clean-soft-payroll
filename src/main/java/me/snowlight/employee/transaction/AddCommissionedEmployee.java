package me.snowlight.employee.transaction;

import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.BiweeklySchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private float salary;
    private float commissionRate;

    public AddCommissionedEmployee(long empId, String name, String address, float salary, float commissionRate) {
        super(empId, name, address);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new CommissionedClassification(this.salary, this.commissionRate);
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new BiweeklySchedule();
    }
}
