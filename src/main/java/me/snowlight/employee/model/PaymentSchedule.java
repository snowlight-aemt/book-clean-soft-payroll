package me.snowlight.employee.model;

import java.time.LocalDate;

public interface PaymentSchedule {
    public boolean isPayDate(LocalDate date);
}
