package com.example.company.service;

import com.example.company.model.Employee;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addNewEmployee(Employee employee) {
        employeeRepository.save(toEntity(employee));
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list_of_employee = new ArrayList<>();
        employeeRepository.findAll().forEach(list_of_employee::add);
        return list_of_employee;
    }

    private Employee toEntity(Employee employee) {
        Employee entity = new Employee();
        entity.setEmployeeName(employee.getEmployeeName());
        entity.setEmployeeRole(employee.getEmployeeRole());
        entity.setEmployeeSalary(employee.getEmployeeSalary());
        return entity;
    }
}
