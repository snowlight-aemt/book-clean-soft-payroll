package me.snowlight.employee.model;

import java.time.LocalDate;

public abstract class PaymentClassification {
    public abstract double calculatePay(PayCheck payCheck);

    protected boolean isInPayPeriod(LocalDate date, PayCheck payCheck) {
        LocalDate payPeriodStartDate = payCheck.getPayPeriodStartDate();
        LocalDate payPeriodEndDate = payCheck.getPayPeriodEndDate();

        return date.isAfter(payPeriodStartDate.minusDays(1))
                && date.isBefore(payPeriodEndDate.plusDays(1));
    }
}
