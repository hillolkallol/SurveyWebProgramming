<%-- 
    Document   : login
    Created on : Nov 23, 2016, 12:39:07 AM
    Author     : KD
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("login_error_msg");  %> 
<html lang="en">
    <head>
        <title>Polling</title>

        <meta charset="UTF-8">
        <meta name="description" content="Polling web application">
        <meta name="keywords" content="Polling, Voting, Feedback">
        <meta name="author" content="bdTigers">
        <link rel="stylesheet" type="text/css" href="style.css">

    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section">
                <img class="logo" src="img/logo.png"><br><br>
                <h2>Sign In</h2><br>
                <form method="post" action="login_checking">
                    <label>Username: </label><input type="text" name="USERNAME" required><br>
                    <label>Password: </label><input type="password" name="PASSWORD" required><br>
                    <label><%= error %></label><br><br>
                    <input type="submit" value="Sign In"><br>
                </form>
                
                <a class="customize_link" href="forget_pass.html">Forget Password?</a>
                <a class="customize_link" href="sign_up_primary.html">Create a new account</a>
            </div>
        </div>
        <div id="footer" class="fixed_footer">
            <div class="footer_body">
                <table class="footer_table">
                    <tr>
                        <td><a class="customize_link" href="about.html">About</a></td>
                        <td><a class="customize_link" href="copyright.html">Copyright</a></td>
                        <td><a class="customize_link" href="terms.html">Terms and Conditions</a></td>
                        <td><a class="customize_link" href="help.html">Helps</a></td>
                        <td><a class="customize_link" href="contact.html">Contact Us</a></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
