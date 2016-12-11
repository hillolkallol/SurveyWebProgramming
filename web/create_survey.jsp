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
            $('.item-content').hide();
            $(document).on('click', '.item-toggle', function (e) {
            e.preventDefault();
            $(this).prev('.item-content').toggle();
            //$( "#div1" ).toggle();
            //alert($(this).prev('.item-content').attr('id'));
            });
            function onLoadFunction()
            {
                localStorage.setItem("numberOfQuestion", 1);
                /*localStorage.setItem("numberOfAns",1);*/
            }
        </script>

    </head>

    <body onload="onLoadFunction()">
        <%@include file="header.jsp" %>
        <div class="body">
            <form method="post" action="create_survey">
                <div class="panel panel-default">
                    <section class="panel-body">
                        <p class="bg-info" style="text-align: center;">Create New Survey</p>
                        <div style="text-align: center;"><input type="text" name="survey_title" placeholder="Servey Title" class="form-control option_input" required></div>
                        <div style="text-align: center; height: 100%"><input type="textarea" name="survey_desc" placeholder="Descriptions of the Servey" class="form-control option_input" rows="5" required=""></div>
                        <!--<div class="tooltip"><a href="create_survey.html"><img class="circular_survey_logo" src="img/logo.jpg"></a>-->
                        <div class="image-upload tooltip">
                            <label for="file-input">
                                <img class ="circular_survey_logo" src="img/logo.jpg"/>
                            </label>
                            <input id="file-input" type="file" />
                            <span class="tooltiptext">Change Logo</span>
                        </div><br>

                        <!-- <input type="button" name="save" value="Save" class="btn btn-primary"> -->
                    </section>
                </div>
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
                        <div class="panel-heading" style="display: table"> <div style="display: table-cell; margin: 5px;">1.</div> <div style="display: table-cell; width: 99%;"><input type="text" placeholder="Question goes here" name="question" class="form-control" required=""></div></div>
                        <!--<div class="panel-heading"> 1. <input type="text" placeholder="Question goes here" name="question" class="form-control"></div> -->
                        <div class="panel-body">
                            <div ><input type="text" placeholder="Option 1 goes here" name="option1" class="form-control option_input" required=""></div>
                            <div ><input type="text" placeholder="Option 2 goes here" name="option2" class="form-control option_input" required=""></div>
                            <div ><input type="text" placeholder="Option 3 goes here" name="option3" class="form-control option_input" ></div>
                            <div ><input type="text" placeholder="Option 4 goes here" name="option4" class="form-control option_input" ></div>
                            <div ><input type="text" placeholder="Option 5 goes here" name="option5" class="form-control option_input" ></div>
                        </div>
                    </div>
                </section>
                <div style="width: 100%; text-align: center; margin-top: -10px;"><input type="button" value="Add Question" name="add_quiestion_panel" id="add_qsn_pnl" class="btn btn-info" style="width: 100%" > </div>
                <div style="width: 100%; margin-top: 20px"><input type="submit" value="Create this Survey" name="submit_create_survey" id="sbmt_crt_srvy" class="btn btn-primary" style="width: 100%"></div>
            </form>
        </div>
        <%@include file="footer.jsp" %>

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
var noOfQsn = localStorage.getItem("numberOfQuestion");
noOfQsn++;
var noOfAns = 1;
var newQues = $('<div class="panel panel-info ">');
var options = "";
options += '<div class="panel-heading" style="display: table"> <div style="display: table-cell; margin: 5px;">' + noOfQsn + '. </div> <div style="display: table-cell; width: 100%;"><input type="text" placeholder="Question goes here" name="question" class="form-control" ></div></div>';
options += '<div class= " panel-body">' ;
options += '<div style="margin: 5px;" id = "div_id_' + noOfQsn + '_' + noOfAns + '"><input type="text" placeholder="Option ' + noOfAns + ' goes here" name="option1" class="form-control item-content"  id = "ans_id_' + noOfQsn + '_' + noOfAns + '"> <!--<input type = "button" value = "+/-" class="item-toggle">--></div>';
noOfAns++;
options += '<div style="margin: 5px;"><input type="text" placeholder="Option ' + noOfAns + ' goes here" name="option2" class="form-control item-content"  id = "ans_id_' + noOfQsn + '_' + noOfAns + '"> <!-- <input type = "button" value = "+/-" class="item-toggle"> --></div>';
noOfAns++;
options += '<div style="margin: 5px;"><input type="text" placeholder="Option ' + noOfAns + ' goes here" name="option3" class="form-control item-content"  id = "ans_id_' + noOfQsn + '_' + noOfAns + '"> <!-- <input type = "button" value = "+/-" class="item-toggle"> --></div>';
noOfAns++;
options += '<div style="margin: 5px;"><input type="text" placeholder="Option ' + noOfAns + ' goes here" name="option4" class="form-control item-content"  id = "ans_id_' + noOfQsn + '_' + noOfAns + '"> <!-- <input type = "button" value = "+/-" class="item-toggle"> --></div>';
noOfAns++;
options += '<div style="margin: 5px;"><input type="text" placeholder="Option ' + noOfAns + ' goes here" name="option5" class="form-control item-content"  id = "ans_id_' + noOfQsn + '_' + noOfAns + '"> <!-- <input type = "button" value = "+/-" class="item-toggle"> --></div>';
noOfAns++;
options += '</div>';
options += '</div>';
newQues.append(options);
$("section.create_survey_section").append(newQues);

window.scrollBy(0, 500);

localStorage.setItem("numberOfQuestion", noOfQsn);

/*var noOfAns = localStorage.getItem("numberOfAns");
 noOfAns++;
 localStorage.setItem("numberOfQuestion",noOfAns);
                 
 localStorage.setItem("numberOfAns",1);*/

// alert(noOfQsn);
});

        </script>
    </body>

</html>
