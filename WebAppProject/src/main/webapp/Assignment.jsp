<%--
  Created by IntelliJ IDEA.
  User: newto
  Date: 5/2/2024
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    /* Style for the form container */
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f4f4f4;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    /* Style for labels */
    label {
        display: block;
        margin-bottom: 8px;
    }

    /* Style for input fields */
    input[type="text"] {
        width: calc(100% - 10px);
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    /* Style for submit button */
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
<script>

    function fetchAssignment() {
        const form = document.getElementById("AssignmentForm");
        form.addEventListener('submit', handleSubmit);
        //REMOVE LATER
        let assId = 1
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `${pageContext.request.contextPath}/api/assignments/`+ assId +`/questions`, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                createFormFromXML(xhr.responseXML);
            }
        };
        xhr.send();
    }

    function handleSubmit(event){
        event.preventDefault();
        const data = new FormData(event.target);
        const value = Object.fromEntries(data.entries());
        const xhr = new XMLHttpRequest();
        xhr.open("POST", `${pageContext.request.contextPath}/api/answers`, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.send(JSON.stringify(value));

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                createFormFromXML(xhr.responseXML);
            }
        };
        xhr.send();
        }


    function createFormFromXML(xml) {

        // Get the <Results> element
        let results = xml.getElementsByTagName("Results")[0];

        // Create a form element
        let form = document.getElementById("AssignmentForm");

        // Iterate through each <Row> element
        let rows = results.getElementsByTagName("Row");
        for (let i = 0; i < rows.length; i++) {
            let row = rows[i];

            // Get question details
            let questionId = row.getElementsByTagName("question_id")[0].textContent;
            let questionText = row.getElementsByTagName("question_text")[0].textContent;

            // Create label and input elements
            let label = document.createElement("label");
            label.innerHTML = questionText;
            let input = document.createElement("input");
            input.type = "text";
            input.name = questionId;
            input.classList.add("answer")

            // Append label and input to form
            form.appendChild(label);
            form.appendChild(input);
            form.appendChild(document.createElement("br")); // Line break between questions
        }

        // Add submit button
        let submitButton = document.createElement("input");
        submitButton.type = "submit";
        submitButton.value = "Submit";
        form.appendChild(submitButton);

        // Append form to the document body
        document.body.appendChild(form);
    }
    document.addEventListener("DOMContentLoaded", fetchAssignment);

</script>
<html>
<head>
    <title>Assignment</title>
</head>
<body>
<H1>Assignment</H1>
<form id="AssignmentForm" class="form-container">

</form>
</body>
</html>
