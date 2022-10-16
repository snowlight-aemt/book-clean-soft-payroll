package me.snowlight.employee.transaction;

import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.WeeklySchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private float hourlyRate;

    public AddHourlyEmployee(Long empId, String name, String address, float hourlyRate) {
        super(empId, name, address);
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new HourlyClassification(this.hourlyRate);
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new WeeklySchedule();
    }
}
