package me.snowlight.employee.model;

import java.time.LocalDate;

public class ServiceCharge {

    private float charge;
    private LocalDate date;

    public ServiceCharge(LocalDate date, float charge) {
        this.charge = charge;
        this.date = date;
    }

    public float getCharge() {
        return this.charge;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
