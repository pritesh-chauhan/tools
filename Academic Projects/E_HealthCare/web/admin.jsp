<%-- 

    Document   : admin
    Created on : Mar 26, 2020, 10:43:50 PM
    Author     : prite

    This is the the Admin Dashboard page where the Admin can select the Updation or Deletion of the 
    Patient, Doctor and Pharmacist.

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout" id="value" style="text-align: justify; float: right;"/>
</form>
</div>
<hr>
<div class="container">
    <h1>Welcome Admin</h1>
    <br><br>
    <a href="adminUpdate.jsp">Update</a>
    &nbsp;
    &nbsp;
    <a href="adminDelete.jsp">Delete</a>

</div>  
<%@include file = "footer.html"%>  