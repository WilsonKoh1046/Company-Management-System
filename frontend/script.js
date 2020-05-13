'use strict'

function addDepartment() {
    let name = document.getElementById("departmentName");
    let profit = document.getElementById("departmentProfit");

    let departmentData = {"departmentName": name.value, "departmentProfit": profit.value};

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/department/add",
        data: JSON.stringify(departmentData),
        contentType: "application/json",
        success: function() {
            name.value = '';
            profit.value = '';
            refreshDepartment();
        },
        error: function(error) {
            alert("Failed to add department");
        }
    })
}

function refreshDepartment() {
    let departmentTable = document.getElementById("department-table").getElementsByTagName("tbody")[0];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/department/allDepartments",
        contentType: "application/json",
        success: function(data) {
            let allId = [];
            for (let i = 0; i < departmentTable.rows.length; i++) {
                allId[i] = departmentTable.rows[i].cells[0].innerHTML;
            }
            for (let i = 0; i < data.length; i++) {
                if (!allId.includes(data[i].departmentId.toString())) {
                    let newDepartmentEntry = departmentTable.insertRow(-1);
                    let newDepartmentID = newDepartmentEntry.insertCell(0);
                    let newDepartmentName = newDepartmentEntry.insertCell(1);
                    let newDepartmentProfit = newDepartmentEntry.insertCell(2);
                    newDepartmentID.innerHTML = data[i].departmentId;
                    newDepartmentName.innerHTML = data[i].departmentName;
                    newDepartmentProfit.innerHTML = data[i].departmentProfit;
                }
            }
        },
        error: function(error) {
            alert("Failed to refresh department table");
        }
    })
}

function submit() {
    let name = document.getElementById("employeeName");
    let role = document.getElementById("employeeRole");
    let salary = document.getElementById("employeeSalary");
    let departmentId = document.getElementById("employeeDepartmentId");

    let employeeData = {"employeeName": name.value, "employeeRole": role.value, "employeeSalary": salary.value, "departmentId": departmentId.value};

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
            departmentId.value = '';
            refresh();
            // alert("Successfully created new employee!");
        },
        error: function(error) {
            alert("Failed to create new employee");
        }
    })
}

function refresh() {
    let employeeTable = document.getElementById("employee-table").getElementsByTagName("tbody")[0];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/employee/allEmployees",
        contentType: "application/json",
        success: function(data) {
            let allId = [];
            for (let i = 0; i < employeeTable.rows.length; i++) {
                allId[i] = employeeTable.rows[i].cells[0].innerHTML;
            }
            console.log(allId);
            for (let i = 0; i < data.length; i++) {
                // Perform check on all the employee ID to ensure no duplication 
                if (!allId.includes(data[i].employeeId.toString())) {
                    let newEmployeeEntry = employeeTable.insertRow(-1);
                    let newEmployeeID = newEmployeeEntry.insertCell(0);
                    let newEmployeeName = newEmployeeEntry.insertCell(1);
                    let newEmployeeRole = newEmployeeEntry.insertCell(2);
                    let newEmployeeSalary = newEmployeeEntry.insertCell(3);
                    let newEmployeeDepartment = newEmployeeEntry.insertCell(4);
                    newEmployeeID.innerHTML = data[i].employeeId;
                    newEmployeeName.innerHTML = data[i].employeeName;
                    newEmployeeRole.innerHTML = data[i].employeeRole;
                    newEmployeeSalary.innerHTML = data[i].employeeSalary;
                    newEmployeeDepartment.innerHTML = data[i].departmentName;
                }
            }
        },
        error: function(error) {
            alert("Failed to refresh employee table");
        }
    })
}