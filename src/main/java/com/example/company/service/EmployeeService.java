package com.example.company.service;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
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
        Optional<Department> targeted_department = departmentRepository.findById(employee.getDepartmentId());
        targeted_department.ifPresent(department -> entity.setDepartmentName(department.getDepartmentName()));
        return entity;
    }
}
