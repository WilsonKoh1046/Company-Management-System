package com.example.company.repository;

import com.example.company.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Query("SELECT departmentName FROM Department WHERE id = :id")
    List<String> getDepNameById(int id);
}
