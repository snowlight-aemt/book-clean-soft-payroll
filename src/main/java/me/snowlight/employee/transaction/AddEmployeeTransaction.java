package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentMethod;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.model.Employee;

public abstract class AddEmployeeTransaction implements Transaction {
    private Long empId;
    private String name;
    private String address;

    @Override
    public void execute() {
        Employee employee = new Employee(empId, this.name, this.address);
        employee.setPaymentClassification(this.getPaymentClassification());
        employee.setPaymentSchedule(this.getPaymentSchedule());
        employee.setPaymentMethod(new HoldMethod());
        GPayrollDatabase.setEmployee(employee);
    }

    public AddEmployeeTransaction(Long empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    abstract protected PaymentClassification getPaymentClassification();

    abstract protected PaymentSchedule getPaymentSchedule();
}
