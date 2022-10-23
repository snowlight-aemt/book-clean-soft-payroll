package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.UnionAffiliation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeMemberTransactionTest {
    @Test
    public void change() {
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(1L, "Bob", "Bob Address", 8.0f);
        addHourlyEmployee.execute();

        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(1L, 7734L, 99.43f);
        changeMemberTransaction.execute();

        Employee employee = GPayrollDatabase.getEmployee(1L);
        assertThat(employee).isNotNull();
        assertThat(employee.getAffiliation().getClass()).isEqualTo(UnionAffiliation.class);
        Employee employee1 = GPayrollDatabase.getUnionMember(7734L);
        assertThat(employee1).isEqualTo(employee);
    }
}
