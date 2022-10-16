package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {
    private Long empId;

    public DeleteEmployeeTransaction(Long empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        GPayrollDatabase.deleteEmployee(this.empId);
    }
}
