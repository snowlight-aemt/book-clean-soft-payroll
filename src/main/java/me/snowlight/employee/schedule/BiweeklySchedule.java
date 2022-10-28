package me.snowlight.employee.schedule;

import me.snowlight.employee.model.PaymentSchedule;

import java.time.LocalDate;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(LocalDate date) {
        return false;
    }

    @Override
    public LocalDate getPayPeriodStartDate(LocalDate endDate) {
        return null;
    }
}
