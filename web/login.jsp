<%-- 
    Document   : login
    Created on : Nov 23, 2016, 12:39:07 AM
    Author     : KD
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("login_error_msg");
        if(error == null)
            error = "";
%> 
<html lang="en">
    <head>
        
        <!-- bootstrap file -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale = 1">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- bootstrap file -->

    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section ">
                <div class="panel-heading"><img class="logo" src="img/logo.png"><br><br>
                    <h2 >Sign In</h2></div>
                <form method="post" action="login_checking" class="panel-body">
                    <input class="form-control" type="text" placeholder="username" name="USERNAME" required><br>
                    <input class="form-control" type="password" placeholder="password" name="PASSWORD" required><br>
                    <label><%= error%></label><br><br>
                    <input class="btn btn-primary" type="submit" value="Sign In"><br>
                </form>

                <a class="customize_link" href="forget_password">Forget Password?</a>
                <a class="customize_link" href="sign_up_primary.jsp">Create a new account</a>
            </div>
        </div>
                    <br><br><br><br><br>
        <%@include file="footer.jsp" %>
    </body>
</html>
