package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.HourlyClassification;
import me.snowlight.employee.classification.SalariedClassification;
import me.snowlight.employee.method.HoldMethod;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PaymentClassification;
import me.snowlight.employee.model.PaymentMethod;
import me.snowlight.employee.model.PaymentSchedule;
import me.snowlight.employee.schedule.MonthlySchedule;
import me.snowlight.employee.schedule.WeeklySchedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddEmployeeTransactionTest {

    @DisplayName("고정 월급 직원 추가")
    @Test
    public void testAddSalariedEmployee() {
        String name = "Bob";
        long empId = 1L;
        var addTransactionEmployee = new AddSalariedEmployee(empId, name, "Bob Address", 1000.0f);
        addTransactionEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        SalariedClassification salariedClassification = (SalariedClassification) employee.getPaymentClassification();
        MonthlySchedule monthlySchedule = (MonthlySchedule) employee.getPaymentSchedule();
        HoldMethod holdMethod = (HoldMethod) employee.getPaymentMethod();

        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo(name);

        assertThat(salariedClassification).isNotNull();
        assertThat(salariedClassification.getClass()).isEqualTo(SalariedClassification.class);
        assertThat(salariedClassification.getSalary()).isEqualTo(1000.0f);

        assertThat(monthlySchedule).isNotNull();
        assertThat(monthlySchedule.getClass()).isEqualTo(MonthlySchedule.class);

        assertThat(holdMethod).isNotNull();
        assertThat(holdMethod.getClass()).isEqualTo(HoldMethod.class);
    }

    @DisplayName("시간 직원 추가")
    @Test
    public void testAddHourlyEmployee() {
        long empId = 2L;
        String name = "rebot";
        var addHourlyEmployee = new AddHourlyEmployee(empId, name, "rebot address", 9.0f);
        addHourlyEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo(name);

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification).isNotNull();
        assertThat(paymentClassification.getClass()).isEqualTo(HourlyClassification.class);

        PaymentSchedule paymentSchedule = employee.getPaymentSchedule();
        assertThat(paymentSchedule).isNotNull();
        assertThat(paymentSchedule.getClass()).isEqualTo(WeeklySchedule.class);

        PaymentMethod paymentMethod = employee.getPaymentMethod();
        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getClass()).isEqualTo(HoldMethod.class);
    }

    
}
