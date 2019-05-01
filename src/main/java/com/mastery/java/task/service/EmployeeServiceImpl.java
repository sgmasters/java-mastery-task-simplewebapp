package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    public Employee findEmployeeById(Long employee_id) {
        return employeeDao.findOne(employee_id);
    }

    public void createEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public void updateEmployee(Employee employee, Long id) {
        employeeDao.update(employee, id);
    }

    public void deleteEmployee(Long employee_id) {
        employeeDao.delete(employee_id);
    }

    public void deleteAllEmployees() {
        employeeDao.deleteAll();
    }

    public boolean isEmployeeExist(Employee employee) {
        return employeeDao.isExist(employee);
    }
}