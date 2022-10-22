package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.ServiceCharge;
import me.snowlight.employee.model.UnionAffiliation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAddServiceCharge {
    @Test
    public void execute() {
        AddHourlyEmployee addHourlyEmployee =
                new AddHourlyEmployee(1L, "Bob", "Bob Address", 8.0f);
        addHourlyEmployee.execute();

        Employee employee = GPayrollDatabase.getEmployee(1L);
        // 주당 조합비는 임금에서 공재되어야 한다.
        // 주당 조합비 비율을 입력
        UnionAffiliation unionAffiliation = new UnionAffiliation(12.5f);
        employee.setAffiliation(unionAffiliation);
        GPayrollDatabase.AddUnionMember(11L, employee);

        // 또한 조합은 가끔 조합원 개인에게 공재액을 주과할 수도 있다.
        long date = 20221022L;
        ServiceChargeTransaction serviceChargeTransaction =
                new ServiceChargeTransaction(11L, date, 12.95f);
        serviceChargeTransaction.execute();

        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(date);
        Assertions.assertThat(serviceCharge.getCharge()).isEqualTo(12.95f);
        Assertions.assertThat(serviceCharge.getDate()).isEqualTo(date);
    }
}
