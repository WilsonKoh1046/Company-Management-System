package com.example.company.service;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.EmployeeRepository;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        entity.setDepartmentId(employee.getDepartmentId());
        return entity;
    }
}
