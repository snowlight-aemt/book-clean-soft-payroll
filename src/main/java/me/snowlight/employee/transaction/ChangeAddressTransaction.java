package me.snowlight.employee.transaction;

import me.snowlight.employee.model.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private String address;

    public ChangeAddressTransaction(Long empId, String address) {
        super(empId);

        this.address = address;
    }

    @Override
    protected void change(Employee employee) {
        employee.setAddress(this.address);
    }
}
