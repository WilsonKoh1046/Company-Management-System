'use strict'

function submit() {
    let name = document.getElementById("employeeName");
    let role = document.getElementById("employeeRole");
    let salary = document.getElementById("employeeSalary");

    let employeeData = {"employeeName": name.value, "employeeRole": role.value, "employeeSalary": salary.value};

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/employee/add",
        data: JSON.stringify(employeeData),
        contentType: "application/json",
        // don't use the below line if the expected response is not JSON type object
        // dataType: "json",
        success: function() {
            name.value = '';
            role.value = '';
            salary.value = '';
            alert("Successfully created new employee!");
        },
        error: function(error) {
            alert("Failed to create new employee");
        }
    })
}

function refresh() {
    let employeeTable = document.getElementById("employee-table");
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/employee/allEmployees",
        contentType: "application/json",
        success: function(data) {
            let allId = [];
            for (let i = 1; i < employeeTable.rows.length; i++) {
                allId[i - 1] = employeeTable.rows[i].cells[0].innerHTML;
            }
            for (let i = 0; i < data.length; i++) {
                // Perform check on all the employee ID to ensure no duplication 
                if (!allId.includes(data[i].employeeId.toString())) {
                    let newEmployeeEntry = employeeTable.insertRow(-1);
                    let newEmployeeID = newEmployeeEntry.insertCell(0);
                    let newEmployeeName = newEmployeeEntry.insertCell(1);
                    let newEmployeeRole = newEmployeeEntry.insertCell(2);
                    let newEmployeeSalary = newEmployeeEntry.insertCell(3);
                    newEmployeeID.innerHTML = data[i].employeeId;
                    newEmployeeName.innerHTML = data[i].employeeName;
                    newEmployeeRole.innerHTML = data[i].employeeRole;
                    newEmployeeSalary.innerHTML = data[i].employeeSalary;
                }
            }
        },
        error: function(error) {
            alert("Failed to refresh employee table");
        }
    })
}