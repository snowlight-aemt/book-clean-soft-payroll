package me.snowlight.employee.model;


import java.time.LocalDate;

public class SalesReceipt {
    private LocalDate date;
    private float amount;

    public SalesReceipt(LocalDate date, float amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public float getAmount() {
        return this.amount;
    }
}
