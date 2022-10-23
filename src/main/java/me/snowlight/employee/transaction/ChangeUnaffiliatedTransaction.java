package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Affiliation;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.NoAffiliation;
import me.snowlight.employee.model.UnionAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    private Long memberId;
    private float dues;

    public ChangeUnaffiliatedTransaction(Long empId, Long memberId, float dues) {
        super(empId);
        this.memberId = memberId;
    }

    @Override
    protected void recordMembership(Employee employee) {
        Affiliation affiliation = employee.getAffiliation();
        if (affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            GPayrollDatabase.RemoveUnionMember(unionAffiliation.getMemberId());
        }
    }


    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}
