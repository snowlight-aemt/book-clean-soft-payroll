package me.snowlight.employee.model;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements  Affiliation {
    private Long memberId;
    private float dues;
    private Map<Long, ServiceCharge> serviceCharges = new HashMap<>();
    public UnionAffiliation(Long memberId, float dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(long date) {
        return this.serviceCharges.get(date);
    }

    public void setServiceCharge(Long date, float charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge));
    }

    public Long getMemberId() {
        return memberId;
    }
}
