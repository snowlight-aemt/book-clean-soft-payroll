package me.snowlight.employee.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements  Affiliation {
    private Long memberId;
    private float dues;
    private Map<LocalDate, ServiceCharge> serviceCharges = new HashMap<>();
    public UnionAffiliation(Long memberId, float dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(LocalDate date) {
        return this.serviceCharges.get(date);
    }

    public void setServiceCharge(LocalDate date, float charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge));
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public double calculateDeductions(PayCheck payCheck) {
        double totalServiceCharge = this.serviceCharges.values().stream()
                .mapToDouble(ServiceCharge::getCharge)
                .sum();
        return this.dues + totalServiceCharge;
    }
}
