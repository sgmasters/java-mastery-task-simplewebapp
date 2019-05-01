package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper {

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setGender(Gender.valueOf(rs.getString("gender")));
        employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        return employee;
    }
}
