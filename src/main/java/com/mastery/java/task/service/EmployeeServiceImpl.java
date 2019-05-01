package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeRepository;
import com.mastery.java.task.dto.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long employee_id) {
        return employeeRepository.findOne(employee_id);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employee_id) {
        employeeRepository.delete(employee_id);
    }

    public boolean isEmployeeExist(Employee employee) {
        return employeeRepository.exists(Example.of(employee));
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }
}