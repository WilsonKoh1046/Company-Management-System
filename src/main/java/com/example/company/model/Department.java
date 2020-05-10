package com.example.company.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "department_auto")
    private int departmentId;
    private String departmentName;
    private int departmentProfit;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentProfit() {
        return departmentProfit;
    }

    public void setDepartmentProfit(int departmentProfit) {
        this.departmentProfit = departmentProfit;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees() {
        this.employees = new ArrayList<>();
    }
}
