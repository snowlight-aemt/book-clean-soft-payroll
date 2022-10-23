package me.snowlight.employee.transaction;

import me.snowlight.employee.model.Affiliation;
import me.snowlight.employee.model.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(Long empId) {
        super(empId);
    }

    @Override
    protected void change(Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }

    protected abstract void recordMembership(Employee employee);
    protected abstract Affiliation getAffiliation();

}
