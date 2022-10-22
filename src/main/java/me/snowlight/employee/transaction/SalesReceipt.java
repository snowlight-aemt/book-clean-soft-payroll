package me.snowlight.employee.transaction;


public class SalesReceipt {
    private Long date;
    private float amount;

    public SalesReceipt(Long date, float amount) {
        this.date = date;
        this.amount = amount;
    }

    public Long getDate() {
        return this.date;
    }

    public float getAmount() {
        return this.amount;
    }
}
