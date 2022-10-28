package me.snowlight.employee.classification;

import me.snowlight.employee.model.PayCheck;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.SalesReceipt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private float salary;
    private float commissionRate;
    private Map<LocalDate, SalesReceipt> salesReceipts = new HashMap<>();

    public CommissionedClassification(float salary, float commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public SalesReceipt getSalesReceipt(LocalDate date) {
        return this.salesReceipts.get(date);
    }

    public void setSalesReceipt(SalesReceipt salesReceipt) {
        this.salesReceipts.put(salesReceipt.getDate(), salesReceipt);
    }

    @Override
    public double calculatePay(PayCheck payCheck) {
        return 0;
    }
}
