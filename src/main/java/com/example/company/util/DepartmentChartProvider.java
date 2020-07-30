package com.example.company.util;

import com.example.company.model.Department;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentChartProvider {

    public static Map<String, List<?>> prepareDataSet(List<Department> departments) {
        Map<String, List<?>> dataset = new HashMap<>();
        dataset.put("Labels", formLabelList(departments));
        dataset.put("Values", formValueList(departments));
        return dataset;
    }

    private static List<String> formLabelList(List<Department> departments) {
        return departments.stream()
                .sorted(Comparator.comparing(d -> d.getEmployees().size()))
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());
    }

    private static List<Integer> formValueList(List<Department> departments) {
        return departments.stream()
                .sorted(Comparator.comparing(d -> d.getEmployees().size()))
                .map(d -> d.getEmployees().size())
                .collect(Collectors.toList());
    }
}
