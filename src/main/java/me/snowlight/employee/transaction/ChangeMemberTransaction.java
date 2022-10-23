package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Affiliation;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private Long memberId;
    private float dues;

    public ChangeMemberTransaction(Long empId, Long memberId, float dues) {
        super(empId);
        this.memberId = memberId;
    }

    @Override
    protected void recordMembership(Employee employee) {
        GPayrollDatabase.AddUnionMember(this.memberId, employee);
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(this.memberId, this.dues);
    }
}
