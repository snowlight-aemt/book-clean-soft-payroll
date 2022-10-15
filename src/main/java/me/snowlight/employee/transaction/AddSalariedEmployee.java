package me.snowlight.employee.transaction;

import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.PaymentMethod;
import me.snowlight.employee.schedule.MonthlyPaymentSchedule;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.classification.SalariedClassification;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private float salary;
    public AddSalariedEmployee(Long empId, String name, String address, float salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new SalariedClassification(this.salary);
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new MonthlyPaymentSchedule();
    }
}
