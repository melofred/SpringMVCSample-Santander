<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <!-- 
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
 -->
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>

 	 <form:form method="POST" action="/saveUser" commandName="user">
        User ID : <form:input type="text" readonly="readonly" path="userid"
            value="${user.userid}" /> <br /> 
        First Name : <form:input
            type="text" path="firstName"
            value="${user.firstName}" /> <br /> 
        Last Name : <form:input
            type="text" path="lastName"
            value="${user.lastName}" /> <br /> 
        DOB : <form:input
            type="text" path="dob"
            value="${user.dob}" /> <br /> 
        Email : <form:input type="text" path="email"
            value="${user.email}" /> <br /> <input
            type="submit" value="Submit" />
    </form:form>
</body>
</html>