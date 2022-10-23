package me.snowlight.employee.transaction;

import me.snowlight.employee.model.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String name;

    public ChangeNameTransaction(Long empId, String name) {
        super(empId);

        this.name = name;
    }

    @Override
    protected void change(Employee employee) {
        employee.setName(this.name);
    }
}
