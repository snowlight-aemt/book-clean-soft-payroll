package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DeleteEmployeeTransactionTest {

    @Test
    void testDeleteEmployeeTransaction() {
        long empId = 1L;
        var addHourlyEmployee = new AddHourlyEmployee(empId, "Box", "Box Address", 9.25f);
        addHourlyEmployee.execute();

        Assertions.assertThat(GPayrollDatabase.getEmployee(empId)).isNotNull();

        DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(empId);
        deleteEmployeeTransaction.execute();

        Assertions.assertThat(GPayrollDatabase.getEmployee(empId)).isNull();
    }
}
