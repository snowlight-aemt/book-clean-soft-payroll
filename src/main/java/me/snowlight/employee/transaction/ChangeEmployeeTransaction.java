package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private Long empId;

    public ChangeEmployeeTransaction(Long empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        Employee employee = GPayrollDatabase.getEmployee(this.empId);
        change(employee);
    }

    protected abstract void change(Employee employee);
}
