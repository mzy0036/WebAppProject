<%--
  Created by IntelliJ IDEA.
  User: newto
  Date: 5/1/2024
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style TYPE="text/css">
    .sidebar {
        width: 10%;
        position: fixed;
        top: 0;
        left: 0;
        padding-top: 40px;
        background-color: lightblue;
        height: 100%;
    }
    .sidebar div {
        padding: 8px;
        font-size: 24px;
        display: block;
    }
    .body {
        margin-left:10%;
        font-size: 18px;
        width: 90%;
        border: grey;
        height: 100%;
    }
    #messageInput{
        position: absolute;
        bottom: 1rem;
        width: 90%;
        height: 2rem;
    }
    #message{
        width:90%;
        height: 100%;
        display: inline-block;
        font-size: 18px;
        border-radius: 3px 0px 0px 3px;
    }
    .button {
        background-color: #04AA6D; /* Green */
        border: none;
        color: white;
        padding: 2px 10px 1px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 14px;
        height: 100%;
    }

</style>
<head>
    <title>Chatroom</title>
</head>
<body>
    <div id="sidebar" class="sidebar">
        <h3>Select a Chatroom</h3>
        <p>Public</p>
<ul>
    <li>Announcements</li>
    <li>General</li>
</ul>
        <p>Direct Messages</p>
        <ul>
            <li></li>
        </ul>
    </div>
<div id="content" class="body">
    <div id="messagesDiv">

    </div>
    <div id="messageInput">
        <input id="message" name="message" type="text" placeholder="type your message here"><button id="sendMsgBtn" class="button" type="button">Send</button>
    </div>
</div>

</body>
</html>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#sendMsgBtn').click(function(e) {
            e.preventDefault();
            var model = { senderID : 1, receiverID: 1, content : "test"};
            $.ajax({
                url: "MessageServlet",
                type: "POST",
                data: model,
                cache: false,
                success: function(data) {
                    debugger;
                    $("#message").html(data).slideDown('slow');
                }
            });
        });
    });
</script>
