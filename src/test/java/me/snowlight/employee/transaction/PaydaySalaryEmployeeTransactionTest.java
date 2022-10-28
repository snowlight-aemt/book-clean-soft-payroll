package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PayCheck;
import org.assertj.core.api.Assertions;
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
        assertThat(payCheck.getPayDate()).isEqualTo(dDay);
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
}
