package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChangeNameTransactionTest {

    @Test
    void change() {
        AddHourlyEmployee addHourlyEmployee =
                new AddHourlyEmployee(1L, "Bill", "Bill Address", 8.0f);
        addHourlyEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo("Bill");

        ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(1L, "Bob");
        changeNameTransaction.execute();

        Employee employee1 = GPayrollDatabase.getEmployee(1L);
        assertThat(employee1.getName()).isEqualTo("Bob");
    }
}
