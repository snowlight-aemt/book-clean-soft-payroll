package me.snowlight.employee.transaction;

import me.snowlight.GPayrollDatabase;
import me.snowlight.employee.model.Employee;
import me.snowlight.employee.model.PayCheck;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private LocalDate date;

    private Map<Long, PayCheck> payChecks = new HashMap<>();
    public PaydayTransaction(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute() {
        for (Employee employee : GPayrollDatabase.getAllEmployees()) {
            if (employee.isPayDate(this.date)) {
                payChecks.put(employee.getEmpId(), employee.payDay(new PayCheck(employee.getPayPeriodStartDate(this.date), this.date)));
            }
        }
    }

    public PayCheck getPayCheck(Long empId) {
        return payChecks.get(empId);
    }
}
