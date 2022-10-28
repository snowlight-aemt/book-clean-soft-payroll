package me.snowlight.employee.model;

import java.time.LocalDate;

public class Employee {
    private Long empId;
    private String name;
    private String address;
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private Affiliation affiliation;

    public Employee(Long empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.affiliation = new NoAffiliation();
    }

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public PaymentSchedule getPaymentSchedule() {
        return this.paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return this.affiliation;
    }

    public boolean isPayDate(LocalDate date) {
        return this.getPaymentSchedule().isPayDate(date);
    }

    public PayCheck payDay(PayCheck payCheck) {
        double grossPay = this.paymentClassification.calculatePay(payCheck);
        double deductions = this.affiliation.calculateDeductions(payCheck);
        double netpay = grossPay - deductions;

        this.paymentMethod.pay(payCheck);

        return new PayCheck(payCheck.getPayDate(), grossPay, deductions, netpay);
    }
}
