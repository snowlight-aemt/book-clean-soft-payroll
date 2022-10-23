package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.method.DirectMethod;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.method.MailMethod;
import me.snowlight.employee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeMailTransactionTest {

    @Test
    public void change() {
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(1L, "Bob", "Bob Address", 8.0f);
        addHourlyEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getPaymentMethod().getClass()).isEqualTo(HoldMethod.class);

        ChangeMailTransaction changeMailTransaction = new ChangeMailTransaction(1L);
        changeMailTransaction.execute();
        assertThat(employee.getPaymentMethod().getClass()).isEqualTo(MailMethod.class);

    }

}
