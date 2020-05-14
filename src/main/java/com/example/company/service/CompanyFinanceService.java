package com.example.company.service;

import com.example.company.model.Department;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyFinanceService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyFinanceService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public int getCompanyProfit() {
        int total_profit = 0;
        List<Department> list_of_department = new ArrayList<>();
        departmentRepository.findAll().forEach(list_of_department::add);
        for (Department department: list_of_department) {
            total_profit += department.getDepartmentProfit();
        }
        return total_profit;
    }
}
