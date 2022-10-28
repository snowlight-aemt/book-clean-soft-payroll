package me.snowlight.employee.classification;

import me.snowlight.employee.model.PayCheck;
import me.snowlight.employee.model.PaymentClassification;

public class SalariedClassification implements PaymentClassification {
    private float salary;

    public SalariedClassification(float salary) {
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        return 0;
    }
}
