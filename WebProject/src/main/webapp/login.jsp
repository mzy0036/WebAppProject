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

<form class="" action="index.jsp" method="post"
      style="text-align: center; margin-left: auto; background-color: lightblue;">
    <label for="email">Email</label>
    <input id="email" type="text" name="email" placeholder="Your Email Address" required><br><br>
    <label for="password">Password</label>
    <input id="password" type="password" name="password" placeholder="Your password" required
           pattern="{6,12}" title="write a 6-12 characters"><br><br>
    <input type="submit" name="Login" />
</form>

<div class="wrapper">
    <!-- content -->
    <div class="push"></div>
</div>
<div class="footer">Copyright © 2022. All Rights Reserved</div>

</body>
</html>
