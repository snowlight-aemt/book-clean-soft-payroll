package me.snowlight.employee.model;

import java.time.LocalDate;

public class PayCheck {
    private double grossPay;
    private double deductions;
    private double netpay;
    private LocalDate payDate;

    public PayCheck(LocalDate payDate) {
        this.payDate = payDate;
    }

    public PayCheck(LocalDate payDate, double grossPay, double deductions, double netpay) {
        this.payDate = payDate;
        this.grossPay = grossPay;
        this.deductions = deductions;
        this.netpay = netpay;
    }

    public LocalDate getPayDate() {
        return this.payDate;
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
}
