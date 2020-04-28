<%-- 
    Document   : register
    Created on : Mar 25, 2020, 1:49:09 PM
    Author     : prite
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="d" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="e" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@include file = "header.html"%>
<%@include file = "sessionInvalidate.jsp"%>
<hr>
<h2>Registration Form</h2>

<div class="container">
    <form action="reg.jsp" method="POST">
        <label for="fname">First Name</label>
        <input type="text" name="fname" style="margin-left: 100px;" required/><br>
        <label for="insno">Insurance Number</label>
        <input type="number" name="insno" style="margin-left: 45px;" required/><br>
        <label for="eid">Email</label>
        <input type="email" name="eid" style="margin-left: 135px;" required/><br>
        <label for="pass">Password</label>
        <input type="password" id="pass" name="pass" style="margin-left: 104px;" required/><br>
        <label for="cpass">Confirm Password</label>
        <input type="password" id="cpass" name="cpass" style="margin-left: 42px;" required/><br>
        <label for="role">Select Role</label>
        <select name="role" id="role" style="margin-left: 89px;" required>
            <!--<option value="admin" required>Admin</option>-->
            <option value="doctor" required>Doctor</option>
            <option value="patient" required>Patient</option>
            <option value="pharmacist" required>Pharmacist</option>
        </select><br>
        <input type="submit" value="Register" style="margin-left: 0px;" onclick="myFunction()"/>
        <br><br>
    </form>
</div>
<%@include file = "footer.html"%>

