<%--
  Created by IntelliJ IDEA.
  User: notst
  Date: 5/2/2024
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<ul id="courseList">
    <!-- Course information will be dynamically added here -->
</ul>

<script>
    function fetchCourses() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "${pageContext.request.contextPath}/api/courses/", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const coursesXml = xhr.responseXML;
                const courses = coursesXml.getElementsByTagName('Results');
                for (let i = 0; i < courses.length; i++) {
                    var course = courses[i];
                    var course_number = course.getElementsByTagName('course_number')[0].textContent;
                    var course_name = course.getElementsByTagName('course_name')[0].textContent;
                    // Create list item and append course information
                    const listItem = document.createElement("li");
                    listItem.textContent = "Course ID: " + course_number + ", Name: " + course_name;
                    courseList.appendChild(listItem);
                }
            }
        };
        xhr.send();
    }
</script>
<form action="api/signup" method="post">
    First Name: <input name="firstName" type="text" required> <br/>
    Last Name: <input name="lastName" type="text" required> <br/>
    Username: <input name="username" type="text" required> <br/>
    Password: <input name="password" type="password" required> <br/>
    Email: <input name="email" type="text" required> <br/>
    Role: <input TYPE="radio" name="role" value="teacher"/>Student
    <input TYPE="radio" NAME="role" VALUE="student"/>Teacher <br/>
</form>
<script> fetchCourses() </script>
</body>
</html>


