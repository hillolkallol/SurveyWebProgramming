<%-- 
    Document   : create_survey
    Created on : Nov 28, 2016, 3:59:38 PM
    Author     : KD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <meta charset="UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="font_awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale = 1">

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script>
            $(function () {
                $("#sortable").sortable();
                $("#sortable").disableSelection();
            });
        </script>

        <title>NMT CSE-321 Project | Online Quiz & Survey System</title>
    </head>

    <body>
        <div id="header">
            <div class="header_body">
                <table class="header_table">
                    <tr>
                        <td>
                            <a href="homepage.html"><img class="container_logo" src="img/logo.png" alt="logo"></a>
                        </td>
                        <td class="center_row"><input type="text" placeholder="Search..." name="search">
                            <a href="search.html"><input type="submit" value="Search"></a>
                        </td>
                        <td class="right_row">
                            <a class="customize_link" href="dashboard.html">Dashboard</a>
                            <div class="tooltip">
                                <a href="${pageContext.request.contextPath}/create_survey"><img class="circular_dashboard" src="img/plus.png"></a>
                                <span class="tooltiptext">Create Survey</span></div>
                            <ul class="menu cf">
                                <li>
                                    <a href="#"><img class="circular_image" src="img/logo.jpg"></a>
                                    <ul class="submenu">
                                        <li><a href="profile.html">My Account (${user})</a></li>
                                        <li><a href="${pageContext.request.contextPath}/logout">Sign Out</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="body">
            <form action="create_survey">
                <section class="create_survey_title">
                    <h2>Create New Survey</h2>
                    <input type="text" name="survey_title" placeholder="Servey Title"><br>
                    <input type="textarea" name="survey_desc" placeholder="Descriptions of the Servey"><br>
                    <!--<div class="tooltip"><a href="create_survey.html"><img class="circular_survey_logo" src="img/logo.jpg"></a>-->
                    <div class="image-upload tooltip">
                        <label for="file-input">
                            <img class ="circular_survey_logo" src="img/logo.jpg"/>
                        </label>
                        <input id="file-input" type="file" />
                        <span class="tooltiptext">Change Logo</span>
                    </div><br>

                    <input type="submit" name="save" value="Save">
                </section>
                <section class="create_survey_section">

                    <div>
                        <!-- <ul id="sortable">
                             <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="text" name="survey_option_1" placeholder="Option 1"></li>
                             <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="text" name="survey_option_2" placeholder="Option 2"></li>
                             <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="text" name="survey_option_3" placeholder="Option 3"></li>
                             <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="text" name="survey_option_4" placeholder="Option 4"></li>
                             <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span><input type="text" name="survey_option_5" placeholder="Option 5"></li>
                         </ul> -->

                    </div>

                    <div class="panel panel-info"> 
                        <div class="panel-heading"><input type="text" placeholder="Question goes here" name="question"></div>
                        <div class="panel-body">
                            <div><input type="text" placeholder="option 1 goes here" name="option1" class="form-control"></div>
                            <div><input type="text" placeholder="option 2 goes here" name="option2" class="form-control"></div>
                            <div><input type="text" placeholder="option 3 goes here" name="option3" class="form-control"></div>
                            <div><input type="text" placeholder="option 4 goes here" name="option4" class="form-control"></div>
                            <div><input type="text" placeholder="option 5 goes here" name="option5" class="form-control"></div>
                        </div>
                    </div>


                </section>
                <input type="button" value="Add Question" name="add_quiestion_panel" id="add_qsn_pnl" class="btn btn-primary">
                <input type="submit" value="Finish" name="submit_create_survey" id="sbmt_crt_srvy" class="btn btn-primary">
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

        <!--<script>
            $("#addrow").click(function () {
                var newRow = $("<tr>");
                var cols = "";
                cols += '<td><input type="text" name="product" /></td>';
                cols += '<td><input type="text" name="price"/></td>';
                cols += '<td><input type="text" name="qty"/></td>';
                cols += '<td><input type="text" name="linetotal" readonly="readonly"/></td>';
                cols += '<td><a class="deleteRow"> x </a></td>';
                newRow.append(cols);
                $("table.order-list").append(newRow);
            });

        </script> -->

        <script>
            $("#add_qsn_pnl").click(function () {
                //var newQues = $("<div style='background-color:blue; width=100%'>");
                var newQues = $('<div class="panel panel-info">');
                var options = "";
                options += '<div class="panel-heading"><input type="text" placeholder="Question goes here" name="question"></div>';
                options += '<div class="panel-body">';
                options += '<div><input type="text" placeholder="option 1 goes here" name="option1" class="form-control"></div>';
                options += '<div><input type="text" placeholder="option 2 goes here" name="option2" class="form-control"></div>';
                options += '<div><input type="text" placeholder="option 3 goes here" name="option3" class="form-control"></div>';
                options += '<div><input type="text" placeholder="option 4 goes here" name="option4" class="form-control"></div>';
                options += '<div><input type="text" placeholder="option 5 goes here" name="option5" class="form-control"></div>';
                options += '</div>';
                options += '</div>';
                newQues.append(options);
                $("section.create_survey_section").append(newQues);
                window.scrollBy(0, 500);
            });

        </script>
    </body>

</html>
