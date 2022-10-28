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
    public void testSingleHourlyEmployeeNoTimeCard() {
        long empId = 2L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Address", 10.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(
                dDay);
        paydayTransaction.execute();

        validateHourlyPayCheck(paydayTransaction, empId, dDay, 0);
    }

    @Test
    public void testSingleHourlyEmployee() {
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

        validateHourlyPayCheck(paydayTransaction, empId, dDay, 5.0f * 10.0f);
    }

    @Test
    public void testSingleHourlyEmployeeOvertimeOneTimeCard() {
        long empId = 2L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Address", 10.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();
        LocalDate fourDaysBeforeDDay = LocalDate.of(2022, Month.OCTOBER, 25);
        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(
                fourDaysBeforeDDay,
                10.0f, empId);
        timeCardTrancation.execute();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(
                dDay);
        paydayTransaction.execute();

        validateHourlyPayCheck(paydayTransaction, empId, dDay, (8.0f + 3.0f) * 10.0f);
    }

    @Test
    public void testSingleHourlyEmployeeTwoTimeCard() {
        long empId = 2L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Address", 10.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();
        LocalDate fourDaysBeforeDDay = LocalDate.of(2022, Month.OCTOBER, 25);
        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(
                fourDaysBeforeDDay,
                2.0f, empId);
        timeCardTrancation.execute();
        LocalDate threeDaysBeforeDDay = LocalDate.of(2022, Month.OCTOBER, 26);
        TimeCardTrancation timeCardTransaction2 = new TimeCardTrancation(
                threeDaysBeforeDDay,
                3.0f, empId);
        timeCardTransaction2.execute();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(
                dDay);
        paydayTransaction.execute();

        validateHourlyPayCheck(paydayTransaction, empId, dDay, (5.0f) * 10.0f);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriod() {
        long empId = 2L;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Address", 10.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();

        LocalDate wrongDate = LocalDate.of(2002, Month.OCTOBER, 25);
        TimeCardTrancation timeCardTrancation = new TimeCardTrancation(
                wrongDate,
                2.0f, empId);
        timeCardTrancation.execute();
        LocalDate threeDaysBeforeDDay = LocalDate.of(2022, Month.OCTOBER, 26);
        TimeCardTrancation timeCardTransaction2 = new TimeCardTrancation(
                threeDaysBeforeDDay,
                3.0f, empId);
        timeCardTransaction2.execute();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(
                dDay);
        paydayTransaction.execute();

        validateHourlyPayCheck(paydayTransaction, empId, dDay, (3.0f) * 10.0f);
    }

    private static void validateHourlyPayCheck(PaydayTransaction paydayTransaction, long empId, LocalDate dDay, float pay) {
        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertThat(payCheck).isNotNull();
        assertThat(payCheck.getPayDate()).isEqualTo(dDay);
        assertThat(payCheck.getGrossPay()).isEqualTo(pay);
        assertThat(payCheck.getDeductions()).isEqualTo(0);
        assertThat(payCheck.getGetNetPay()).isEqualTo(pay);
    }


}

