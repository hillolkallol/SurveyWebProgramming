<%-- 
    Document   : profile
    Created on : Nov 28, 2016, 10:03:35 PM
    Author     : KD
--%>

<%@page import="com.survey.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- User userDetails = new User();
    userDetails = (User) request.getAttribute("userDetails");  --%> 
<% String message = " ";
    if((String) request.getAttribute("update_message") != null)
        message = (String) request.getAttribute("update_message");  
%> 
<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="font_awesome/css/font-awesome.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>NMT CSE-321 Project | Online Quiz & Survey System</title>
	</head>

	<body>
		<div id="header">
			<div class="header_body">
				<table class="header_table">
					<tr>
						<td><a href="homepage.html"><img class="container_logo" src="img/logo.png" alt="logo"></a></td>
						<td class="center_row"><input type="text" placeholder="Search..." name="search"><a href="search.html"><input type="submit" value="Search"></a></td>
						<td class="right_row">
							<a class="customize_link" href="dashboard.html">Dashboard</a>
							<div class="tooltip"><a href="create_survey.html"><img class="circular_dashboard" src="img/plus.png"></a>
							<span class="tooltiptext">Create Survey</span></div>
							<ul class="menu cf">
							  <li>
							    <a href="#"><img class="circular_image" src="img/logo.jpg"></a>
							    <ul class="submenu">
							      <li><a href="profile.html">My Account</a></li>
							      <li><a href="login.html">Sign Out</a></li>
							    </ul>
							  </li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="body min_height">
			<form method="post" action="profile">
                            <table class="profile_body_section profile_created_by_me_table">
                                <tr>
                                    <td>Email</td>
                                    <td><input type="text" name="email" value="${userDetails.getEmail_address()}" required></td>
                                </tr>
                                <tr>
                                    <td>Username</td>
                                    <td><input type="text" name="user_name" value="${userDetails.getUsername()}" disabled></td>
                                </tr>
                                <tr>
                                    <td>First Name</td>
                                    <td><input type="text" name="f_name" value="${userDetails.getFirst_name()}" required></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td><input type="text" name="l_name" value="${userDetails.getLast_name()}" required></td>
                                </tr>
                                <tr>
                                    <td>Old Password</td>
                                    <td><input type="password" name="old_pass" value="" required></td>
                                </tr>
                                <tr>
                                    <td>New Password</td>
                                    <td><input type="password" placeholder="Keep BLANK if you don't want to change the password!" name="new_pass" value=""></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><label><%= message %></label></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" name="save" value="Save"></td>
                                </tr>
                            </table>
			</form>
		</div>
		<div id="footer">
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