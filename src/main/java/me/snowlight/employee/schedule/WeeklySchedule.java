package me.snowlight.employee.schedule;

import me.snowlight.employee.model.PaymentSchedule;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Long date) {
        return false;
    }
}
