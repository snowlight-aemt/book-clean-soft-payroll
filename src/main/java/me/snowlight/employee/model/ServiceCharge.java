package me.snowlight.employee.model;

public class ServiceCharge {

    private float charge;
    private Long date;

    public ServiceCharge(Long date, float charge) {
        this.charge = charge;
        this.date = date;
    }

    public float getCharge() {
        return this.charge;
    }

    public Long getDate() {
        return this.date;
    }
}
