package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.schedule.BiweeklySchedule;
import me.snowlight.employee.schedule.MonthlySchedule;
import me.snowlight.employee.schedule.WeeklySchedule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeHourlyTransactionTest {
    @Test
    public void change() {
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(1L, "Bob", "Bob Address", 125.0f, 5.0f);
        addCommissionedEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getPaymentClassification().getClass()).isEqualTo(CommissionedClassification.class);
        assertThat(employee.getPaymentSchedule().getClass()).isEqualTo(BiweeklySchedule.class);

        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(1L, 9.0f);
        changeHourlyTransaction.execute();
        assertThat(employee.getPaymentClassification().getClass()).isEqualTo(HourlyClassification.class);
        assertThat(employee.getPaymentSchedule().getClass()).isEqualTo(WeeklySchedule.class);

    }

}
