'use strict'

// Call functions upon reloading the page
window.onload = () => {
    refresh();
    refreshDepartment();
}

// Detect the screen resolution to determine the layout of department and employee section
$(window).resize(() => {
    if ($(window).width() <= 767) {
        let departmentForm = document.getElementById("department-form");
        let departmentInfo = document.getElementById("department-info");
        let employeeForm = document.getElementById("employee-form");
        let employeeInfo = document.getElementById("employee-info");
        departmentForm.classList.remove("col-4");
        departmentForm.classList.add("col");
        departmentInfo.classList.remove("col-8");
        departmentInfo.classList.add("col");
        employeeForm.classList.remove("col-4");
        employeeForm.classList.add("col");
        employeeInfo.classList.remove("col-8");
        employeeInfo.classList.add("col");
    }
})

function buildDepartmentChart() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/department/chart-dataset",
        contentType: "application/json",
        success: function(dataset) {
            document.getElementById("departmentChart").removeAttribute("style");
            let ctx = document.getElementById("departmentChart").getContext("2d");
            let chart = new Chart(ctx, {
                type: "doughnut",
                data: {
                    labels: dataset["Labels"],
                    datasets: [{
                        data: dataset["Values"],
                        backgroundColor: randomColorGenerator(dataset["Labels"].length)
                    }]
                }
            })
        },
        error: function(error) {
            errorMessage("department", "Failed to create chart");
        }
    })
}

function randomColorGenerator(length) {
    let colors = [];
    for (let i = 0; i < length; i++) {
        let r = Math.round(Math.random() * 255);
        let g = Math.round(Math.random() * 255);
        let b = Math.round(Math.random() * 255);
        colors.push(`rgb(${r}, ${g}, ${b})`);
    }
    return colors;
}

function updateCompanyProfit() {
    let profit = document.getElementById("company-profit");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/finance/getCompanyProfit",
        contentType: "application/json",
        success: function(data) {
            console.log(data);
            profit.innerHTML = "$" + data;
        },
        error: function(error) {
            // alert("Failed to update company's profit");
            errorMessage("company-finance", "Failed to update company's profit");
        }
    })
}

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
            // alert("Failed to add department");
            errorMessage("department", "Failed to add department");
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
                    let newDepartmentNumberOfEmployee = newDepartmentEntry.insertCell(3);
                    newDepartmentID.innerHTML = data[i].departmentId;
                    newDepartmentName.innerHTML = data[i].departmentName;
                    newDepartmentProfit.innerHTML = data[i].departmentProfit;
                    newDepartmentNumberOfEmployee.innerHTML = data[i].employees.length;
                }
            }
            updateCompanyProfit();
            updateNumberOfEmployee(data);
        },
        error: function(error) {
            // alert("Failed to refresh department table");
            errorMessage("department", "Failed to refresh department table");
        }
    })
}

function updateNumberOfEmployee(data) {
    let departmentTable = document.getElementById("department-table").getElementsByTagName("tbody")[0];
    let allNumberOfEmployee = [];
    for (let i = 0; i < data.length; i++) {
        allNumberOfEmployee[i] = data[i].employees.length;
    }
    for (let j = 0; j < departmentTable.rows.length; j++) {
        let last_cell = departmentTable.rows[j].cells[departmentTable.rows[j].cells.length - 1];
        if (last_cell.innerHTML != allNumberOfEmployee[j]) {
            last_cell.innerHTML = allNumberOfEmployee[j];
        }
    }
}

function submit() {
    let name = document.getElementById("employeeName");
    let role = document.getElementById("employeeRole");
    let salary = document.getElementById("employeeSalary");
    let departmentId = document.getElementById("employeeDepartmentId");

    let employeeData = {"employeeName": name.value, "employeeRole": role.value, "employeeSalary": salary.value, "depId": departmentId.value};

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
            refreshDepartment();
            // alert("Successfully created new employee!");
        },
        error: function(error) {
            // alert("Failed to create new employee");
            errorMessage("employee", "Failed to create new employee");
        }
    })
}

function getDepartmentName(depId) {
    return $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/department/name/${depId}`
    });
}

function refresh() {
    let employeeTable = document.getElementById("employee-table").getElementsByTagName("tbody")[0];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/employee/allEmployees",
        contentType: "application/json",
        success: async function(data) {
            let allId = [];
            for (let i = 0; i < employeeTable.rows.length; i++) {
                allId[i] = employeeTable.rows[i].cells[0].innerHTML;
            }
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

                    try {
                        let departmentName = await getDepartmentName(data[i].depId);
                        let depName = departmentName[0];
                        newEmployeeDepartment.innerHTML = depName;
                    } catch(err) {
                        console.error(err);
                    }
                }
            }
            buildDepartmentChart();
        },
        error: function(error) {
            // alert("Failed to refresh employee table");
            errorMessage("employee", "Failed to refresh employee table");
        }
    })
}

function errorMessage(sectionId, message) {
    let section = document.getElementById(sectionId);
    let messageBox = document.createElement("div");
    let messageText = document.createElement("p");
    messageBox.classList.add("alert");
    messageBox.classList.add("alert-dismissible");
    messageBox.classList.add("alert-danger");
    messageText.innerHTML = message;
    messageBox.appendChild(messageText);
    section.prepend(messageBox);
    // make the message box disapper after 2 seconds
    (() => {
        setTimeout(() => {
            messageBox.remove();
        }, 2000);
    })();
}