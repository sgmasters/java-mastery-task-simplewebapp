package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // -------------------Retrieve All Employees------------------------------------------

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> listAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // -------------------Retrieve Single Employee------------------------------------------

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
        LOGGER.info("Fetching Employee with id {}", id);
        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            LOGGER.error("Employee with id {} not found.", id);
            return new ResponseEntity<>(new RestException("Employee with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }

    // -------------------Create an Employee-------------------------------------------

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        LOGGER.info("Creating Employee : {}", employee);

        if (employeeService.isEmployeeExist(employee)) {
            LOGGER.error("Unable to create. An Employee with name {} already exist", employee.getFirstName() + "" + employee.getLastName());
            return new ResponseEntity<>(new RestException("Unable to create. An Employee with name " +
                    employee.getFirstName() + "" + employee.getLastName() + " already exist."), HttpStatus.CONFLICT);
        }
        employeeService.createEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/employees/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update an Employee ------------------------------------------------

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        LOGGER.info("Updating Employee with id {}", id);

        Employee currentEmployee = employeeService.findEmployeeById(id);

        if (currentEmployee == null) {
            LOGGER.error("Unable to update. Employee with id {} not found.", id);
            return new ResponseEntity<>(new RestException("Unable to update. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setDepartmentId(employee.getDepartmentId());
        currentEmployee.setJobTitle(employee.getJobTitle());
        currentEmployee.setGender(employee.getGender());
        currentEmployee.setDateOfBirth(employee.getDateOfBirth());

        employeeService.updateEmployee(currentEmployee, id);
        return new ResponseEntity<>(currentEmployee, HttpStatus.ACCEPTED);
    }

    // ------------------- Delete an Employee-----------------------------------------

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
        LOGGER.info("Fetching & Deleting Employee with id {}", id);

        Employee employee = employeeService.findEmployeeById(id);
        if (employee == null) {
            LOGGER.error("Unable to delete. Employee with id {} not found.", id);
            return new ResponseEntity<>(new RestException("Unable to delete. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
    }

    // ------------------- Delete All Employees-----------------------------

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> deleteAllEmployees() {
        LOGGER.info("Deleting All Employees");

        employeeService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}