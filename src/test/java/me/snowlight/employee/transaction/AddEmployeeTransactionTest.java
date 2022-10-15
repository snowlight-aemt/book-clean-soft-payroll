package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.SalariedClassification;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.schedule.MonthlyPaymentSchedule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddEmployeeTransactionTest {

    @Test
    public void testAddSalariedEmployee() {
        String name = "Bob";
        long empId = 1L;
        var addTransactionEmployee = new AddSalariedEmployee(empId, name, "Bob Address", 1000.0f);
        addTransactionEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        MonthlyPaymentSchedule monthlySchedule = (MonthlyPaymentSchedule) employee.getPaymentSchedule();
        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();

        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo(name);

        assertThat(salariedClassification).isNotNull();
        assertThat(salariedClassification.getClass()).isEqualTo(SalariedClassification.class);
        assertThat(salariedClassification.getSalary()).isEqualTo(1000.0f);

        assertThat(monthlySchedule).isNotNull();
        assertThat(monthlySchedule.getClass()).isEqualTo(MonthlyPaymentSchedule.class);

        assertThat(holdMethod).isNotNull();
        assertThat(holdMethod.getClass()).isEqualTo(HoldMethod.class);
    }
}
