
<%@page import="com.survey.db.MySQLConnection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<!DOCTYPE html>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>

<html>

	<head>
		<meta charset="UTF-8">
		<link href="style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="font_awesome/css/font-awesome.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>NMT CSE-321 Project | Online Quiz & Survey System</title>
	</head>

	<body>
            <%
                // Get the current data from the survey table.
             ResultSet res = null;
             ResultSet rs = null;
             String running1 = "";
             String runTitle = "";
             String runDesc = "";
             ResultSet upRes = null;
             try {
//                Class.forName("com.mysql.jdbc.Driver");
//                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "");
                java.sql.Connection con = MySQLConnection.connect();
                String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

                PreparedStatement ps = con.prepareStatement("SELECT  * FROM survey_details WHERE "
                 + "publish_time <='"+currentTime+"' AND closing_time >='"+currentTime+"'");
                
                 res = ps.executeQuery();
                 Statement st = con.createStatement();
                   
                 rs = st.executeQuery("SELECT  * FROM survey_details WHERE "
                 + "publish_time <='"+currentTime+"' AND closing_time >= '"+currentTime+"'");
                 
                 Statement upSt = con.createStatement();
                 
                 upRes = upSt.executeQuery("SELECT  * FROM survey_details WHERE "
                 + "publish_time >='"+currentTime+"'");
                 //upRes.next();
                
                 
                 
                
            } catch (Exception e) {
                System.out.println(e);
            }
                
            %>
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
							      <li><a href="login.jsp.html">Sign Out</a></li>
							    </ul>
							  </li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="body">
			<section class="homepage_body_section">
				<h2>Running and Hot</h2>
                                <%if(rs.next()){%>
				<a href="running_survey.html">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
                                                            
                                                            <h2><%= rs.getString(2) %></h2>
								<p><%= rs.getString(3) %></p>
							</div>
							<div class="section_item_part3">
								Date: <%= rs.getString(6) %><br>
								Participant # 100
							</div>
						</div>
					</section>
				</a><%}%>
                                <%if(rs.next()){%> 
                                <a href="running_survey.html">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
                                                        
                                                            <h2><%=rs.getString(2)%></h2>
								<p><%= rs.getString(3)%></p>
							</div>
							<div class="section_item_part3">
								Date: <%= rs.getString(6) %><br>
								Participant # 100
							</div>
						</div>
					</section>
				</a><%}%>
				<a href="see_more.jsp"><input type="submit" name="see_more" value="See More"></a> 
			</section>

			<section class="homepage_body_section">
				<h2>Upcomming</h2>
                                <%if(upRes.next()){%>
				<a href="#">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<h2><%= upRes.getString(2) %></h2>
								<p><%= upRes.getString(3)%></p>
							</div>
							<div class="section_item_part3">
								Date: <%= upRes.getString(6) %><br>
							</div>
						</div>
					</section>
				</a><%}%>
                                <%if(upRes.next()){%> 
				<a href="#">
					<section class="section_item">
						<div class="section_item_table">
							<div class="section_item_part1">
								<img class="circular_image" src="img/logo.jpg" alt="logo">
							</div>
							<div class="section_item_part2">
								<h2><%= upRes.getString(2) %></h2>
								<p><%= upRes.getString(3) %></p>
							</div>
							<div class="section_item_part3">
								Date: <%= upRes.getString(6) %><br>
							</div>
						</div>
					</section>
				</a><%}%>
			</section>

			<section class="homepage_body_section">
				<h2>Recommandation</h2>
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