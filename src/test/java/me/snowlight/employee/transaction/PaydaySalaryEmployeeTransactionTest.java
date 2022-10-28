package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PayCheck;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class PaydaySalaryEmployeeTransactionTest {
    @Test
    public void paydaySingleSalaryEmployee() {
        Long empId = 1L;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Address", 100f);
        addSalariedEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 31);
        PaydayTransaction paydayTransaction = new PaydayTransaction(dDay);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertThat(payCheck).isNotNull();
        assertThat(payCheck.getPayPeriodEndDate()).isEqualTo(dDay);
        assertThat(payCheck.getGrossPay()).isEqualTo(100f);
    }
    @Test
    public void paydaySingleSalaryEmployeeOnWrongDAte() {
        Long empId = 1L;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Address", 100f);
        addSalariedEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 28);
        PaydayTransaction paydayTransaction = new PaydayTransaction(dDay);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertThat(payCheck).isNull();
    }

    @Test
    public void SalariedUnionMemberDues() {
        long empId = 2L;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Address", 1000f);
        addSalariedEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();

        long memberId = 999L;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.0f);
        changeMemberTransaction.execute();

        LocalDate dDay = LocalDate.of(2022, Month.OCTOBER, 31);
        PaydayTransaction paydayTransaction = new PaydayTransaction(dDay);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPayCheck(empId);
        assertThat(payCheck).isNotNull();
        assertThat(payCheck.getPayPeriodEndDate()).isEqualTo(dDay);
        assertThat(payCheck.getGrossPay()).isEqualTo(1000f);
        assertThat(payCheck.getDeductions()).isEqualTo(9.0f * 5);
        assertThat(payCheck.getGetNetPay()).isEqualTo(1000f - (9.0f * 5));
    }
}
