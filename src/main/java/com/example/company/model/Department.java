package com.example.company.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;
    private int departmentProfit;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "departmentName", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;

    public int getDepartmentId() {
        return id;
    }

    public void setDepartmentId(int departmentId) {
        this.id = departmentId;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
