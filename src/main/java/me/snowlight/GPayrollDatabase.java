package me.snowlight;

import me.snowlight.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class GPayrollDatabase {
    private final static Map<Long, Employee> employeeDic = new HashMap<>();
    public static Employee getEmployee(long empId) {
        return employeeDic.get(empId);
    }

    public static void setEmployee(Employee employee) {
        employeeDic.put(employee.getEmpId(), employee);
    }
}
