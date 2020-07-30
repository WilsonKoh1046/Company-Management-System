package com.example.company.service;

import com.example.company.model.Department;
import com.example.company.model.Employee;
import com.example.company.repository.DepartmentRepository;
import com.example.company.util.DepartmentChartProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<String> getDepNameById(int id) {
        Optional<List<String>> targeted_department = Optional.ofNullable(departmentRepository.getDepNameById(id));
        if (targeted_department.isEmpty()) {
            throw new NoSuchElementException("Department not found");
        }
        return targeted_department.get();
    }

    public Map<String, List<?>> prepareDepartmentChartData() {
        List<Department> list_of_departments = new ArrayList<>();
        departmentRepository.findAll().forEach(list_of_departments::add);
        return DepartmentChartProvider.prepareDataSet(list_of_departments);
    }

    private Department toEntity(Department department) {
        Department entity = new Department();
        entity.setDepartmentId(department.getDepartmentId());
        entity.setDepartmentName(department.getDepartmentName());
        entity.setDepartmentProfit(department.getDepartmentProfit());
        entity.setEmployees(department.getEmployees());
        return entity;
    }
}
