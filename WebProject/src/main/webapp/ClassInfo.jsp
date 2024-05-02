<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <style type="text/css">
        h1{
            font-family: JosefinSans;
        }

    </style>
    <title>Student Registration System</title>
</head>

<body class="bg-light text-white">
<div class="bg-dark text-white">
    <header>
        <h1 class="pt-4 pb-3" style="text-align: center;">Student
            Registration</h1>
    </header>
</div>

<div class="container">
    <div class="row" style="height: 700px">
        <div class="card col-md-4">
            <div class="pt-4">
                <div class="nav flex-column nav-pills" id="v-pills-tab"
                     role="tablist" aria-orientation="vertical">
                    <a class="nav-link"
                       data-toggle="pill" href="CourseReg.jsp" role="tab"
                       aria-controls="v-pills-home" aria-selected="false">Home</a>
                    <a	class="nav-link" data-toggle="pill"
                          href="profile.jsp" role="tab" aria-controls="v-pills-profile"
                          aria-selected="false">Profile</a>
                    <a class="nav-link active"
                       data-toggle="pill" href="ClassInfo.jsp"
                       role="tab" aria-controls="v-pills-profile" aria-selected="true">Class
                        Information</a>
                    <a class="nav-link"
                       data-toggle="pill" href="enroll.jsp" role="tab"
                       aria-controls="v-pills-settings" aria-selected="false">Enroll
                        in a Class</a>
                    <a class="nav-link" id="v-pills-settings-tab"
                       data-toggle="pill" href="disenroll.jsp" role="tab"
                       aria-controls="v-pills-settings" aria-selected="false">Dis-enroll
                        from a Class</a>
                </div>
            </div>
        </div>

        <div class="card col-md-8">
            <div class="form-group">
                <form method="get" action="Stud_Reg_Servlet">
                    <h5 class="mb-0 pt-4 text-dark">Enter Classid to find TA:</h5>
                    <input type="text" class="form-control form-control-sm"
                           name="classid" maxlength="5"> <br>
                    <button type="submit" class="btn btn-primary" name="call_value"
                            value="find_ta">Submit</button>
                    <br>
                </form>
            </div>
            <div class="card-header">
                <h5 class="text-dark"></h5>
            </div>
            <div class="card-body">

            </div>
        </div>
    </div>
</div>

</body>
</html>