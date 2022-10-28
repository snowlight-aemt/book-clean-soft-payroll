package me.snowlight.employee.schedule;

import me.snowlight.employee.model.PaymentSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }
}
