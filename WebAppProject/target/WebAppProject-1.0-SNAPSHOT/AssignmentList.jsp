<%--
  Created by IntelliJ IDEA.
  User: newto
  Date: 5/2/2024
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #assignmentTable {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 90%;
        margin: auto;
        margin-top:1rem;
    }

    #assignmentTable td, #assignmentTable th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #assignmentTable tr:nth-child(even){background-color: #f2f2f2;}

    #assignmentTable tr:hover {background-color: #ddd;}

    #assignmentTable th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #04AA6D;
        color: white;
    }
    #selectDiv{
        text-align: center;
    }

</style>
<html>
<head>
    <title>Create Assignment</title>
</head>
<script>

    function fetchAssignment() {
        let select = document.getElementById("assignment-dropdown");
        select.addEventListener("change", (event) => {
            filter(event);
        });

        //REMOVE LATER
        let userId = 2
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `${pageContext.request.contextPath}/api/users/`+ userId +`/assignments`, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let xml = xhr.responseXML.childNodes[0];
                updateAssignmentTable(xml);
            }
        };
        xhr.send();
    }

    function updateAssignmentTable(assignments) {
        let table = document.getElementById("assignmentTable")
        table.innerHTML = "";

        const headerRow = document.createElement("tr");
        const headers = ["Assignment ID", "Assignment Name", "Course ID","Description", "Open", "Due", "Actions"];

        headers.forEach((header) => {
            const th = document.createElement("th");
            th.textContent = header;
            headerRow.appendChild(th);
        });

        table.appendChild(headerRow);
        assignments.childNodes.forEach((assignment) => {
            const row = document.createElement("tr");
            let btn = document.createElement("a")
            btn.innerText = "View"
            btn.setAttribute("href","/Assignment?assigment=" + assignment.getElementsByTagName("assignment_id")[0].textContent)
            const cells = [
                assignment.getElementsByTagName("assignment_id")[0].textContent,
                assignment.getElementsByTagName("name")[0].textContent,
                assignment.getElementsByTagName("course_id")[0].textContent,
                assignment.getElementsByTagName("description")[0].textContent,
                new Date(assignment.getElementsByTagName("open_date")[0].textContent).toDateString(),
                new Date(assignment.getElementsByTagName("due_date")[0].textContent).toDateString()

            ];

            cells.forEach((cell) => {
                const td = document.createElement("td");
                td.textContent = cell;
                row.appendChild(td);
            });
            const td = document.createElement("td");
            td.appendChild(btn)
            row.appendChild(td)

            table.appendChild(row);
        });
    }

    function filter(showAll){
        let table = document.getElementById("assignmentTable")
        let rows = document.getElementsByTagName("tr")
        for (let i of rows) {
            if(i.firstChild.nodeName === "TD") {
                let data = i.getElementsByTagName("td")
                let start = new Date(data[4].innerHTML).getTime()
                let due = new Date(data[5].innerHTML).getTime()
                let curr = new Date().getTime()
                if(showAll.target.options[0].selected){
                    i.hidden = false
                }else if (!(start <= curr && due >= curr)) {
                    i.hidden = true;
                }
            }
        }
    }

    document.addEventListener("DOMContentLoaded", fetchAssignment);

</script>

<body>
<h1>Assignments</h1>
<div class="form-container" id="login-register">
    <!-- Content will be populated by JavaScript -->
</div>
<Div id="selectDiv">Filter:
<select class=".filter" id="assignment-dropdown">
    <option selected>All</option>
<option>Available</option>
</select>
</Div>

<table id="assignmentTable"></table>
</body>
</html>
