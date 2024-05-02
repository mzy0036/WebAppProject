<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>LEAP Education</title>
</head>
<header>

  <div class='div1'>
    <img src="images/logo.jpg" alt="">
    <h1>Math Education Online System</h1>
  </div>

</header>
<link href="css/style.css" rel="stylesheet">
<link href="css/1.css" rel="stylesheet">
<link href="css/2.css" rel="stylesheet">
<link href="css/3.css" rel="stylesheet">
<link href="css/4.css" rel="stylesheet">
<link href="css/5.css" rel="stylesheet">
<link href="css/6.css" rel="stylesheet">
<link href="css/7.css" rel="stylesheet">
<link href="css/logo.css" rel="stylesheet">
<body>
<div id="topbar">
  <ul>
    <li><a href="index.jsp">HOME</a></li>
    <li><a href="CourseReg.jsp">Online Courses</a></li>
    <li><a href="PersonalLearning.jsp">Personalized Learning 6-12</a></li>
    <li><a href="#Support">Support</a></li>
    <li><a href="chatroom.jsp">Chat Room</a></li>
    <li><a href="login.jsp" class="button" style="background-color:lightblue;margin-left:300px">Sign in</a></li>
    <li><a href="Registration.jsp" class="button" style="background-color:lightblue; margin-left:30px">Sign up</a></li>

  </ul>
</div>

<form class="" action="index.html" method="POST" style="text-align: center; margin-left: auto;">
  <p style="color:blueviolet">Leave a message for help</p>

  <p>Topic: <input type="text" name=""></p><br>

  <div>
    <label>Question type:</label>
    <select name="app" id="app-select">
      <option value="">--Please choose an app--</option>
      <option value="">Assignment</option>
      <option value="">Quiz</option>
    </select>
  </div> <br><br>

  </div>
  <label>Choose Class:</label>
  <select name="class" id="class-select">
    <option value="">--Please choose class--</option>
    <option value="class">Algebra</option>
    <option value="class">Geometry</option>
    <option value="class">Others</option>
  </select>
  <p>Release Date:<input type="date" name="" placeholder="Release Date">&nbsp&nbsp
    <input type="time" name="" placeholder="Time">
  <p>Due Date:<input type="date" name="" placeholder="Due Date">&nbsp&nbsp
    <input type="time" name="" placeholder="Time">
  </p>
  <p>Content: <input type="text" placeholder="Please post here" style="height:200px; width:300px"></p>
  <br><br>

  <input type="submit" name="" value="Post" onclick="validate()">
  </div> <br><br>

</form>
</body>
</html>

