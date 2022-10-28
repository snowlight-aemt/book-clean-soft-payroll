package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Affiliation;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.ServiceCharge;
import me.snowlight.employee.model.UnionAffiliation;

import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {
    private Long memberId;
    private LocalDate date;
    private float charge;

    public ServiceChargeTransaction(Long memberId, LocalDate date, float charge) {
        this.memberId = memberId;
        this.date = date;
        this.charge = charge;
    }

    @Override
    public void execute() {
        Employee member = GPayrollDatabase.getUnionMember(this.memberId);
        Affiliation affiliation = member.getAffiliation();
        if (affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            unionAffiliation.setServiceCharge(this.date, this.charge);
        }
    }
}
