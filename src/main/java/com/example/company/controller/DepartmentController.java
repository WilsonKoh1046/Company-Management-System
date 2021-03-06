package com.example.company.controller;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createNewDepartment(@RequestBody Department department) {
        departmentService.createNewDepartment(department);
        return new ResponseEntity<>("Department created", HttpStatus.CREATED);
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<?> getDepNameById(@PathVariable int id) {
        return new ResponseEntity<>(departmentService.getDepNameById(id), HttpStatus.OK);
    }

    @GetMapping("/chart-dataset")
    public ResponseEntity<?> getDepartmentChartDataset() {
        return new ResponseEntity<>(departmentService.prepareDepartmentChartData(), HttpStatus.OK);
    }
}
