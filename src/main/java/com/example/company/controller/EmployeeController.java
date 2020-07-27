package com.example.company.controller;

import com.example.company.model.Employee;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addNewEmployee(employee);
            return new ResponseEntity<>("Employee added", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
