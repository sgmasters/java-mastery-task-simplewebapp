package com.mastery.java.task.rest;

import com.mastery.java.task.App;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/1", Employee.class);
        System.out.println(employee.getFirstName());
        assertNotNull(employee);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Pavel");
        employee.setLastName("Menshikov");
        employee.setDepartmentId(1);
        employee.setJobTitle("Epam");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth(LocalDate.of(1987, 12, 10));

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", employee, Employee.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        employee.setFirstName("Pavel");
        employee.setLastName("Menshikov");
        employee.setDepartmentId(1);
        employee.setJobTitle("Epam");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth(LocalDate.of(1987, 12, 10));

        restTemplate.put(getRootUrl() + "/employees/" + id, employee);

        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(employee);

        restTemplate.delete(getRootUrl() + "/employees/" + id);

        try {
            restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
