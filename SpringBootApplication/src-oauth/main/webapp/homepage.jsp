<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ser422.sneha.web.model.Article" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>


<%

        Map<String, String> articleHrefMap = (Map) request.getAttribute("articleURIMap");

%>

<%

    String errorMessage = response.getHeader("invalid");
    errorMessage = errorMessage == null ? "" : errorMessage;

%>



<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>home page</title>
</head>

<body style ="background-color:F0E0B0">

<center>
<form method="post" action="/lab2task2/LoginServlet">

<H1> Welcome to NEW news Inc. </H1>

<h2> Hi !! </h2>

<h2> Click on the link to read the article </h2>

<h1 style="color:red;">
    <%= errorMessage %>
</h1>

 <% for(Map.Entry<String, String> entry : articleHrefMap.entrySet()) { %>
        <a href= "<%=entry.getKey() %>"> <%= entry.getValue() %> </a>
        <br><br>
    <% } %>


<br>
<br>

<center>
</form>

<center>
<form method="post" action="/lab2task2/LoginPage.jsp">
<input type="submit" value="Login">
</form>
</center>


<center>
<form method="post" action="/lab2task2/SignUpPage.jsp">
<input type="submit" value="Sign Up">
</form>
</center>

<center>
<form method="post" action="/lab2task2/LogoutServlet">
    <input type="submit" value="Logout">
</form>
</center>


</body>
</html>


