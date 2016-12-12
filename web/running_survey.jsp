<%-- 
    Document   : running_survey
    Created on : Dec 3, 2016, 8:42:30 PM
    Author     : Ratul
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.survey.models.BeanQuestionModule"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <jsp:useBean id="beanSurveyModule" scope="application" class="com.survey.models.BeanSurveyModule" />
        <title>Survey:: ${beanSurveyModule.surveyTitle}</title>
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

    </head>
    <body>
        
        <div id ="attending_survey" class="body">
            <form action="running_survey" method="POST">
                <input type="hidden" value="result_survey" name="hidden_action">
                <div class="panel panel-default">
                    <div id="survey_info" class="panel-body">
                        <div style="text-align: center;"><h2>${beanSurveyModule.surveyTitle}</h2></div>
                        <div style="text-align: center;"><h5>${beanSurveyModule.surveyDesc}</h5></div>
                    </div>
                </div>
                <div class="panel-group"> 
                    <input type="hidden" name="survey_id" value="${beanSurveyModule.surveyID}">
                    <c:set var="qsnCounter" value="1" scope="page" />
                    <c:set var="idName" value="id_" scope="page" />
                    <c:forEach items="${beanSurveyModule.questionModules}" var= "questionModules">
                        <c:set var="optionName" value="${idName}${qsnCounter}" />
                        <div class="panel panel-info"> 

                            <div class="panel-heading"> <strong>${qsnCounter}.  ${questionModules.questionDesc} </strong></div>

                            <div class="panel-body">
                                <c:set var="optionCounter" value="1" scope="page" />

                                <c:forEach items="${questionModules.options}" var="options">
                                    <c:set var="optionValue" value="${idName}${qsnCounter}_${optionCounter}" />
                                    <c:if test="${not empty options.optionDesc}">
                                        <div ><input type="radio" name="selected_option_${questionModules.questionId}" value="${questionModules.questionId}_ ${options.optionId}">  ${options.optionDesc}</div>

                                        <c:set var="optionCounter" value="${optionCounter + 1}" scope="page"/>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <c:set var="qsnCounter" value="${qsnCounter + 1}" scope="page"/>
                    </c:forEach>
                    <div style="width: 100%;" class="panel"><input type="submit" value="Finish" name="submit_create_survey" id="sbmt_crt_srvy" class="btn btn-primary" style="width: 100%"></div>
                </div>   
            </form>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
