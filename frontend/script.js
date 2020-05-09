'use strict'

function submit() {
    let name = document.getElementById("employeeName").value;
    let role = document.getElementById("employeeRole").value;
    let salary = document.getElementById("employeeSalary").value;

    let employeeData = {"employeeName": name, "employeeRole": role, "employeeSalary": salary};

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/employee/add",
        data: JSON.stringify(employeeData),
        contentType: "application/json",
        dataType: "json",
    })
    alert("Successfully created new employee!");
}