package me.snowlight.employee.schedule;

import me.snowlight.employee.model.PaymentSchedule;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(LocalDate date) {
        return getPayPeriodStartDate(date).equals(date);
    }

    @Override
    public LocalDate getPayPeriodStartDate(LocalDate endDate) {
        return endDate.with(TemporalAdjusters.lastDayOfMonth());
    }
}
