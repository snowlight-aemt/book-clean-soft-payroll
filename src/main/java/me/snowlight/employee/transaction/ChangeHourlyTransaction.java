package me.snowlight.employee.transaction;

import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private float hour;

    public ChangeHourlyTransaction(Long empId, float hour) {
        super(empId);
        this.hour = hour;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(this.hour);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
