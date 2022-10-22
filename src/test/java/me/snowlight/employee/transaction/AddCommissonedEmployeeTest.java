package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCommissonedEmployeeTest {
    @Test
    public void execute() {
        long empId = 1L;
        String name = "ComBob";
        AddCommissionedEmployee addCommissionedEmployee =
                new AddCommissionedEmployee(empId, name, "ComBob address", 105.0f, 5.0f);

        addCommissionedEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(empId);
        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo(name);
    }
}
