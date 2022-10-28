package me.snowlight;

import me.snowlight.employee.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPayrollDatabase {
    private final static Map<Long, Employee> employees = new HashMap<>();
    private final static Map<Long, Employee> members = new HashMap<>();
    public static List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public static Employee getEmployee(long empId) {
        return employees.get(empId);
    }

    public static void addEmployee(Long empId, Employee employee) {
        employees.put(empId, employee);
    }

    public static void deleteEmployee(Long empId) {
        employees.remove(empId);
    }

    public static void AddUnionMember(long memberId, Employee employee) {
        members.put(memberId, employee);
    }

    public static Employee getUnionMember(Long memberId) {
        return members.get(memberId);
    }

    public static void RemoveUnionMember(Long memberId) {
        members.remove(memberId);
    }
}
