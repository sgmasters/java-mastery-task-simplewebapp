const containerId = "listOfEmployees";

const listContainer = document.getElementById(containerId);

const path = "/simplewebapp/employees";

let messageTextList = {
    201: "Employee Created",
    202: "Accepted",
    204: "No Employees found",
    400: "Inter correct data",
    404: "Employee with such id not found",
    405: "Inter Id and Employee data",
    409: "Employee with such id already exists",
    500: "Error"
};

let response;

let readMe;

let original;

window.onload = function getOriginReadMe() {
    readMe = document.getElementById("readMe");
    original = readMe.innerHTML;
}

function highlightReadMe() {
    readMe.innerHTML = "<span class='light'>" + readMe.innerHTML + "</span>";
}

function deleteLight() {
    readMe.innerHTML = original;
}

function displayResponse(request) {
    if (request.readyState == 4 && request.status == 200) {
        response = makeJsonPretty(request.responseText);
        deleteLight();
    } else {
        response = messageTextList[request.status];
        highlightReadMe();
    }
    listContainer.innerHTML = response;
};

function makePrettySingleEmployee(val) {
    let keys = Object.keys(val);
    let html = "";
    html += "<div>";
    keys.forEach(function (key) {
        html += "<strong>" + key + "</strong>: " + val[key] + "<br>";
    });
    html += "</div><br>";
    return html;
}

function makeJsonPretty(text) {
    let json = JSON.parse(text);
    let html = "";
    if (Array.isArray(json)) {
        json.forEach(function (json) {
            html = makePrettySingleEmployee(json);
        });
    } else {
        html = makePrettySingleEmployee(json);
    }
    return html;
}

function loadList() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", path, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function loadOneById() {
    const id = document.getElementById("employeeId").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", path + "/" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function deleteAll() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", path, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function deleteOne() {
    const id = document.getElementById("employeeId").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", path + "/" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function createEmployee() {
    const employee = getEmployeeJSON();
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", path, true);
    xhttp.setRequestHeader("Content-type", "application/JSON");
    xhttp.send(employee);
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function updateEmployee() {
    const employee = getEmployeeJSON();
    const id = document.getElementById("employeeId").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", path + "/" + id, true);
    xhttp.setRequestHeader("Content-type", "application/JSON")
    xhttp.send(employee);
    xhttp.onreadystatechange = function () {
        displayResponse(this)
    };
};

function getEmployeeJSON() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const departmentId = document.getElementById("departmentId").value;
    const jobTitle = document.getElementById("jobTitle").value;
    const gender = document.getElementById("gender").value;
    const dateOfBirth = document.getElementById("dateOfBirth").value.split("/").map(Number).reverse();
    let employee = {
        "firstName": firstName,
        "lastName": lastName,
        "departmentId": departmentId,
        "jobTitle": jobTitle,
        "gender": gender,
        "dateOfBirth": dateOfBirth
    };
    return JSON.stringify(employee);
};