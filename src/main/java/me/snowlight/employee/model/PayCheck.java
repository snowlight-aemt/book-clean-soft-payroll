package me.snowlight.employee.model;

import java.time.LocalDate;

public class PayCheck {
    private LocalDate payPeriodStartDate;
    private double grossPay;
    private double deductions;
    private double netpay;
    private LocalDate payPeriodEndDate;

    public PayCheck(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public PayCheck(LocalDate payPeriodEndDate, double grossPay, double deductions, double netpay) {
        this.payPeriodEndDate = payPeriodEndDate;
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netpay = netpay;
    }

    public LocalDate getPayPeriodEndDate() {
        return this.payPeriodEndDate;
    }

    public double getGrossPay() {
        return this.grossPay;
    }

    public double getDeductions() {
        return this.deductions;
    }

    public double getGetNetPay() {
        return this.netpay;
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }
}
