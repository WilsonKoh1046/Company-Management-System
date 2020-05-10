package com.example.company.controller;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/allDepartments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDepartment(@RequestBody Department department) {
        departmentService.createNewDepartment(department);
    }

    @PostMapping("/{departmentId}/addNewEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEmployeeToDepartment(@PathVariable("departmentId") int departmentId,
                                           @RequestBody Employee employee) {
        departmentService.addNewEmployeeToDepartment(departmentId, employee);
    }
}
