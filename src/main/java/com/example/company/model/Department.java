package com.example.company.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int departmentId;
    private String departmentName;
    private int departmentProfit;
    private ArrayList<Integer> employees; // List<> won't work

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

    public ArrayList<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(int employeeId) {
        this.employees.add(employeeId);
    }
}
