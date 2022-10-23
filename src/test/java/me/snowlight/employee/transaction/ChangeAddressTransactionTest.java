package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChangeAddressTransactionTest {
    @Test
    void change() {
        AddHourlyEmployee addHourlyEmployee =
                new AddHourlyEmployee(1L, "Bill", "Bill Address", 8.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getAddress()).isEqualTo("Bill Address");

        ChangeAddressTransaction changeStringTransaction = new ChangeAddressTransaction(1L, "Bob Address");
        changeStringTransaction.execute();

        Employee employee1 = GPayrollDatabase.getEmployee(1L);
        assertThat(employee1.getAddress()).isEqualTo("Bob Address");
    }
}
