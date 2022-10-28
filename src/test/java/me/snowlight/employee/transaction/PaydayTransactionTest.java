package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PayCheck;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class PaydayTransactionTest
{
    @Test
    public void testSingleTimeCardHourlyEmployee() {
        long empId = 2L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Address", 10.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();
        LocalDate fourDaysBeforeDDay = LocalDate.of(2022, Month.OCTOBER, 25);
        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(
                fourDaysBeforeDDay,
                                                                5.0f, empId);
        timeCardTrancation.execute();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(
                dDay);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertThat(payCheck).isNotNull();
        assertThat(payCheck.getPayDate()).isEqualTo(dDay);
        assertThat(payCheck.getGrossPay()).isEqualTo(5.0f * 10.0f);
        assertThat(payCheck.getDeductions()).isEqualTo(0);
        assertThat(payCheck.getGetNetPay()).isEqualTo(5.0f * 10.0f);
    }

}
