package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public EmployeeDao(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Employee employee) {
        String SQL = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().name(),
                Date.valueOf(employee.getDateOfBirth())
        );
    }

    public Employee findOne(Long id) {
        String SQL = "SELECT * FROM employee WHERE employee_id = ?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new EmployeeMapper());
        return employee;
    }

    public List<Employee> findAll() {
        String SQL = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(SQL, new EmployeeMapper());
        return employees;
    }

    public void delete(Long id) {
        String SQL = "DELETE FROM employee WHERE employee_id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Employee with id: " + id + " successfully removed");
    }

    public void update(Employee employee, Long id) {
        String SQL = "UPDATE employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=?, date_of_birth=? WHERE employee_id = ?";
        jdbcTemplate.update(SQL,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().name(),
                Date.valueOf(employee.getDateOfBirth()),
                id
        );
        System.out.println("Employee with id: " + id + " successfully updated.");
    }

    public void deleteAll() {
        String SQL = "DELETE FROM employee *";
        jdbcTemplate.update(SQL);
        System.out.println("Employees removed");
    }

    public boolean isExist(Employee employee) {
        String SQL = "SELECT FROM employee WHERE first_name=? AND last_name=?";
        List employeeFromDatabase = jdbcTemplate.query(SQL,
                new Object[]{
                        employee.getFirstName(),
                        employee.getLastName(),
                },
                new EmployeeMapper());
        return !employeeFromDatabase.isEmpty();
    }
}
