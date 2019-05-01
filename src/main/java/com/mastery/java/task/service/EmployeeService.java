package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee findEmployeeById(Long employee_id);

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee, Long id);

    void deleteEmployee(Long employee_id);

    void deleteAllEmployees();

    boolean isEmployeeExist(Employee employee);
}