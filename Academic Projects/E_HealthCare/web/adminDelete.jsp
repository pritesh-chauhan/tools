<%-- 
    Document   : admin
    Created on : Mar 26, 2020, 10:43:50 PM
    Author     : prite

    Admin deletion page
    The data is obtained using the JDBC Prepared Statement.

--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.html"%>  
<%@include file = "sessionInvalidate.jsp"%>
<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" style="text-align: justify; float: right;"/>
    </form>
</div>
<hr>

<div class="container">
    <h1>Delete User</h1>
    <form action="admin.jsp" method="post">
        <input type="submit" value="Go Back" style="text-align: justify; float: right;"/>
    </form>
</div>

<% 
    String email = (String)request.getParameter("email");
    String password = (String)request.getParameter("password");
%>


<table border="2" width="100%" style="text-align: center;">  
    <th>ID</th>  
    <th>Email ID</th>
    <th>Password</th>
    <th>FullName</th>
    <th>Role</th>
    <th>Actions</th>
    <%
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/health?characterEncoding=latin1&useConfigs=maxPerformance";
        String USER = "root";
        String PASSWORD = "password";
        conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        System.out.println("Connection"+conn);
        String sql = "SELECT id, fullname, email, password, role FROM USERS where role!='admin';";
        PreparedStatement stmt=conn.prepareStatement(sql);  
        ResultSet rs = stmt.executeQuery();
        while(rs.next())
        {
    %>
        <tr>  
            <form action="delete.jsp" method="post">
                <input type="hidden" name="old_id" value="<%= rs.getString("id")%>" readonly />
                <td><input type="text" name="id" value="<%= rs.getString("id")%>" readonly /></td>  
                <td><input type="text" name="email" value="<%= rs.getString("email")%>" readonly /></td>
                <td><input type="text" name="password"  value="<%= rs.getString("password")%>" readonly /></td>
                <input type="hidden" name="old_fullname" value="<%= rs.getString("fullname")%>" readonly />
                <td><input type="text" name="fullname"  value="<%= rs.getString("fullname")%>" readonly /></td>
                <td><input type="text" name="role" value="<%= rs.getString("role")%>" readonly /></td>
                <td><input type="submit" value="Delete" /></td>
            </form>
        </tr>  
    <%}%>
</table>
       
</div>
<%@include file = "footer.html"%>  