<%-- 
    Document   : login
    Created on : Mar 25, 2020, 1:32:58 PM
    Author     : prite

    User login page

--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
</div>
<hr><br><br>

<h1>Login Page</h1>
<div class="container">
    <form action="LoginController" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" style="margin-left: 28px;" size="30" />
        <br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" size="30" />
        <br>
        <br><br>           
        <input type="submit" value="Login" style="margin-left: 0px;" onclick="myFunction()"/>
        &nbsp;&nbsp;    
    </form>
    <br><br>
    <form action="register.jsp">
        <input type="submit" value="Register" style="margin-left: 0px;" onclick="myFunction()"/>
    </form>
</div>
<%@include file = "footer.html"%>  