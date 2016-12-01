<%-- 
    Document   : dashboard
    Created on : Nov 30, 2016, 10:42:53 PM
    Author     : KD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
		<div class="body">
			<section class="dashboard_body_section">
				<h2>Created By Me</h2>
				<table class="dashboard_created_by_me_table">
					<tr>
						<th>Title</th>
						<th>Date</th>
						<th># of Participent</th>
						<th class="dashboard_created_by_me_table_column4">Status</th>
					</tr>
					<tr>
						<td><a href="detailed_view.html">Customer Feedback</a></td>
						<td>10/10/2016</td>
						<td>100</td>
						<td class="dashboard_created_by_me_table_column4">
							<input type="submit" name="publsih" value="Publish"> 
							<input type="submit" name="pause" value="Pause"> 
							<input type="submit" name="stop" value="Stop"> 
						</td>
					</tr>
					<tr>
						<td><a href="detailed_view.html">Customer Satisfaction Survey</a></td>
						<td>10/10/2016</td>
						<td>100</td>
						<td class="dashboard_created_by_me_table_column4">
							<input type="submit" name="publsih" value="Publish"> 
							<input type="submit" name="pause" value="Pause"> 
							<input type="submit" name="stop" value="Stop"> 
						</td>
					</tr>
					<tr>
						<td><a href="detailed_view.html">Customer Feedback</a></td>
						<td>10/10/2016</td>
						<td>100</td>
						<td class="dashboard_created_by_me_table_column4">
							<input type="submit" name="publsih" value="Publish"> 
							<input type="submit" name="pause" value="Pause"> 
							<input type="submit" name="stop" value="Stop"> 
						</td>
					</tr>
				</table>
			</section>

			<section class="dashboard_body_section">
				<h2>Participated by Me</h2>
				<a href="running_survey.html">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<h2>Customer Satisfaction Survey</h2>
								<p>This survey is about customer satisfaction on a E-Business Company</p>
							</div>
							<div class="section_item_part3">
								Date: 10/10/16<br>
								Participant # 100
							</div>
						</div>
					</section>
				</a>
				<a href="running_survey.html">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<h2>Customer Feedback</h2>
								<p>Coustomer feedback on a particular E-Business Company</p>
							</div>
							<div class="section_item_part3">
								Date: 10/10/16<br>
								Participant # 100
							</div>
						</div>
					</section>
				</a>
				<a href="see_more.html"><input type="submit" name="see_more" value="See More"></a> 
			</section>
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
