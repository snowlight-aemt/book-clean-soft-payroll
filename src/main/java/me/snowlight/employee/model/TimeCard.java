package me.snowlight.employee.model;

import java.time.LocalDate;

public class TimeCard {
    private LocalDate date;
    private float hours;

    public TimeCard(LocalDate date, float hours) {
        this.date = date;
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getHours() {
        return hours;
    }
}
