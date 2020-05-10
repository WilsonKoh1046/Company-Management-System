package com.example.company.service;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void createNewDepartment(Department department) {
        departmentRepository.save(toEntity(department));
    }

    public List<Department> getAllDepartments() {
        List<Department> list_of_departments = new ArrayList<>();
        departmentRepository.findAll().forEach(list_of_departments::add);
        return list_of_departments;
    }

    public void addNewEmployeeToDepartment(int departmentId, Employee employee) {
        Optional<Department> targeted_department = departmentRepository.findById(departmentId);
        if (targeted_department.isPresent()) {
            targeted_department.get().addEmployee(toEmployee(employee));
            departmentRepository.save(targeted_department.get());
        }
    }

    private Employee toEmployee(Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(employee.getEmployeeId());
        newEmployee.setEmployeeName(employee.getEmployeeName());
        newEmployee.setEmployeeRole(employee.getEmployeeRole());
        newEmployee.setEmployeeSalary(employee.getEmployeeSalary());
        return newEmployee;
    }

    private Department toEntity(Department department) {
        Department entity = new Department();
        entity.setDepartmentName(department.getDepartmentName());
        entity.setDepartmentProfit(department.getDepartmentProfit());
        entity.setEmployees();
        return entity;
    }
}
