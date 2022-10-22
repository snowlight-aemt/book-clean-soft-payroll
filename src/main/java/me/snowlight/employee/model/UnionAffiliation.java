package me.snowlight.employee.model;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements  Affiliation {
    private float dues;
    private Map<Long, ServiceCharge> serviceCharges = new HashMap<>();
    public UnionAffiliation(float dues) {
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(long date) {
        return this.serviceCharges.get(date);
    }

    public void setServiceCharge(Long date, float charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge));
    }
}
