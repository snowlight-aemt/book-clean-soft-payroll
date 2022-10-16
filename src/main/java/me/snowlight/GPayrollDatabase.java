package me.snowlight;

import me.snowlight.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class GPayrollDatabase {
    private final static Map<Long, Employee> employees = new HashMap<>();
    public static Employee getEmployee(long empId) {
        return employees.get(empId);
    }

    public static void addEmployee(Long empId, Employee employee) {
        employees.put(empId, employee);
    }
}
