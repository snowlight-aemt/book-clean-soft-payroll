package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.method.DirectMethod;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.schedule.BiweeklySchedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChangeDirectTransactionTest {

    @Test
    public void change() {
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(1L, "Bob", "Bob Address", 8.0f);
        addHourlyEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getPaymentMethod().getClass()).isEqualTo(HoldMethod.class);

        ChangeDirectTransaction changeDirectTransaction = new ChangeDirectTransaction(1L);
        changeDirectTransaction.execute();
        assertThat(employee.getPaymentMethod().getClass()).isEqualTo(DirectMethod.class);

    }

}
