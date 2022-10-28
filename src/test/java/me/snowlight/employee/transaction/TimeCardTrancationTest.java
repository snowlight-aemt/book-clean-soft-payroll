package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.TimeCard;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;

import static org.assertj.core.api.Assertions.assertThat;

class TimeCardTrancationTest {

    @Test
    void execute() {
        long empId = 1L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "address", 25.0f);
        addHourlyEmployee.execute();


        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(LocalDate.of(2022, Month.OCTOBER, 28), 8.0f, empId);
        timeCardTrancation.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        HourlyClassification paymentClassification = (HourlyClassification) employee.getPaymentClassification();
        TimeCard timeCard = paymentClassification.getTimeCard(LocalDate.of(2022, Month.OCTOBER, 28));
        assertThat(timeCard).isNotNull();
        assertThat(timeCard.getHours()).isEqualTo(8.0f);
    }
}
