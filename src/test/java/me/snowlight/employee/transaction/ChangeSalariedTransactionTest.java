package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.classification.SalariedClassification;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.schedule.BiweeklySchedule;
import me.snowlight.employee.schedule.MonthlySchedule;
import me.snowlight.employee.schedule.WeeklySchedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChangeSalariedTransactionTest {

    @Test
    public void change() {
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(1L, "Bob", "Bob Address", 8.0f);
        addHourlyEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(1L);
        Assertions.assertThat(employee).isNotNull();
        assertThat(employee.getPaymentClassification().getClass()).isEqualTo(HourlyClassification.class);
        assertThat(employee.getPaymentSchedule().getClass()).isEqualTo(WeeklySchedule.class);

        ChangeSalariedTransaction changeSalariedTransaction = new ChangeSalariedTransaction(1L, 150.0f);
        changeSalariedTransaction.execute();
        assertThat(employee.getPaymentClassification().getClass()).isEqualTo(SalariedClassification.class);
        assertThat(employee.getPaymentSchedule().getClass()).isEqualTo(MonthlySchedule.class);

    }

}
