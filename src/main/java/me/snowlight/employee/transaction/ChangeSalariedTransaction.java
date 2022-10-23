package me.snowlight.employee.transaction;

import me.snowlight.employee.classification.SalariedClassification;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.BiweeklySchedule;
import me.snowlight.employee.schedule.MonthlySchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private float salary;

    public ChangeSalariedTransaction(Long empId, float salary) {
        super(empId);

        this.salary = salary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(this.salary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
