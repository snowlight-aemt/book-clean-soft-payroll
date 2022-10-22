package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.classification.CommissionedClassification;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.SalesReceipt;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesReceiptTransactionTest {

    @Test
    public void execute() {
        long empId = 1L;
        String name = "ComBob";
        AddCommissionedEmployee addCommissionedEmployee =
                new AddCommissionedEmployee(empId, name, "ComBob address", 105.0f, 5.0f);
        addCommissionedEmployee.execute();
        Employee employee = GPayrollDatabase.getEmployee(empId);

        long date = 20221016L;
        float amount = 4.0f;
        SalesReceiptTransaction salesReceiptTransaction =
                new SalesReceiptTransaction(empId, date, amount);
        salesReceiptTransaction.execute();
        new SalesReceiptTransaction(empId, 20221017L, 6.0f).execute();

        assertThat(employee).isNotNull();
        CommissionedClassification commissionedClassification =
                (CommissionedClassification) employee.getPaymentClassification();

        SalesReceipt salesReceipt = commissionedClassification.getSalesReceipt(date);
        assertThat(salesReceipt).isNotNull();
        assertThat(salesReceipt.getDate()).isEqualTo(date);
        assertThat(salesReceipt.getAmount()).isEqualTo(amount);
        SalesReceipt salesReceipt_20221017 = commissionedClassification.getSalesReceipt(20221017L);
        assertThat(salesReceipt_20221017).isNotNull();
        assertThat(salesReceipt_20221017.getDate()).isEqualTo(20221017L);
        assertThat(salesReceipt_20221017.getAmount()).isEqualTo(6.0f);

    }
}
