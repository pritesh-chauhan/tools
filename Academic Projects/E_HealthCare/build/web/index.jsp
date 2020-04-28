<%-- 
    Document   : index.jsp
    Created on : Mar 26, 2020, 3:06:45 PM
    Author     : prite

    Application Homepage

--%>

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
</div>
        <hr><br><br>
    </body>
</html>

<div class="container">
    <a href="register.jsp">Register</a>&nbsp;&nbsp;
    <a href="login.jsp">Login</a>
</div>
<%@include file = "footer.html"%>

