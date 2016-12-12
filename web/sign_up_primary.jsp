<%-- 
    Document   : sign_up_primary
    Created on : Dec 7, 2016, 8:09:00 PM
    Author     : KD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("error_message");
        if(error == null)
            error = "";
%> 
<!DOCTYPE html>
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
        <div class="login_page_body">
            <div class="sign_up_section">
                <div class="panel-heading"><img class="logo" src="img/logo.png"><br><br>
                    <h2 >Sign Up</h2></div>
                <form id = "sign_pri" action="sign_up_primary_controller.jsp" method="POST" class="panel-body">
                <h3><%= error%></h3><br>
                <!--<div id="sign_up_name_panel" style=" width: 100%;">-->
                <input class="form-control" type="text" name="first_name" required placeholder="First Name" /><br>
                <input class="form-control" type="text" name="last_name" placeholder="Last Name"/><br>
                <!--</div>-->
                <!--<input type="email" name="email" placeholder="E-Mail Address" id="email" onblur="checkExist()"/><br>-->
                <input class="form-control" type="email" name="email" placeholder="E-Mail Address" id="email"/><br>
                <span id="isE"></span>
                <input class="form-control" type="email" name="re-email" placeholder="Re-Type E-Mail" id="confirm_email" required/><br>
                <input class="form-control" type="password" name="password" placeholder="Password" id="password" required/><br>
                <input class="form-control" type="password" name="re-password" placeholder="Re-Type Password" id="confirm_password" required/><br>
                <input class="btn btn-primary" type="submit" value="Next"><br>
                </form>
                <a class="customize_link" href="login">Already have an account?</a>
            </div>
        </div>
        <%@include file="footer.jsp" %>
         <script>
            function checkExist(){
                var xmlhttp = new XMLHttpRequest();
                var username = document.forms["sign_pri"]["email"].value;
                var url = "exists.jsp?email=" + username;
                xmlhttp.onreadystatechange = function(){
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                        if(xmlhttp.responseText.replace(/^\s+|\s+$/g, '') === "User already exists")
                            document.getElementById("isE").style.color = "red";
                        else
                            document.getElementById("isE").style.color = "green";
                        document.getElementById("isE").innerHTML = xmlhttp.responseText;
                    }
                    
                };
                try{
                xmlhttp.open("GET",url,true);
                xmlhttp.send();
            }catch(e){alert("unable to connect to server");
            }
        }
        
                
        var password = document.getElementById("password"), confirm_password = document.getElementById("confirm_password");
        function validatePassword(){
            if(password.value != confirm_password.value) {
                 confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
                 confirm_password.setCustomValidity('');
            }
        }

        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;
        
        var email = document.getElementById("email"), confirm_email = document.getElementById("confirm_email");
        function validateEmail(){
            if(email.value != confirm_email.value) {
                 confirm_email.setCustomValidity("Emails Don't Match");
            } else {
                 confirm_email.setCustomValidity('');
            }
        }

        email.onchange = validateEmail;
        confirm_email.onkeyup = validateEmail;
        </script>
    </body>
</html>