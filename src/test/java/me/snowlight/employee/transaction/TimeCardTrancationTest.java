package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TimeCardTrancationTest {

    @Test
    void execute() {
        long empId = 1L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "address", 25.0f);
        addHourlyEmployee.execute();

        long date = 20221016L;

        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(date, 8.0f, empId);
        timeCardTrancation.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        HourlyClassification paymentClassification = (HourlyClassification) employee.getPaymentClassification();
        TimeCard timeCard = paymentClassification.getTimeCard(date);
        assertThat(timeCard).isNotNull();
        assertThat(timeCard.getHours()).isEqualTo(8.0f);
    }
}
