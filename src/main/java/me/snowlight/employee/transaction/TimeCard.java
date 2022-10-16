package me.snowlight.employee.transaction;

import java.time.LocalDate;

public class TimeCard {
    private Long date;
    private float hours;

    public TimeCard(Long date, float hours) {
        this.date = date;
        this.hours = hours;
    }

    public Long getDate() {
        return date;
    }

    public float getHours() {
        return hours;
    }
}
