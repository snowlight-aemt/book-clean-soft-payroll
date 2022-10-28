package me.snowlight.employee.model;

public class NoAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(PayCheck payCheck) {
        return 0f;
    }
}
