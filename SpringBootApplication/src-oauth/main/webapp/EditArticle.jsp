<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="ser422.sneha.web.model.Article" %>

<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<%
            String name = (String) request.getSession().getAttribute("name");
                    if(name == null){
                        response.setHeader("invalid", "Please Log in..Cannot access without Login");
                        request.getRequestDispatcher("homepage.jsp").forward(request, response);
                    }


            String articleid = (String) request.getSession().getAttribute("articleID");
            String content = (String) request.getSession().getAttribute("articlecontent");
%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>home page</title>
</head>

<body style ="background-color:F0E0B0">
<center>

<H1> Welcome to NEW news Inc. </H1>

<form method="post" action="/lab2task2/ArticleServlet" >

ARTICLE ID:
<input type="text" name="aid" value="<%=articleid%>" readonly><br><br>

ARTICLE-CONTENT:
<textarea name="articlecontent" rows="20" cols="100" >
<%=content%>
</textarea><br><br>

<input type="hidden" id="methodName" name="methodName" value="put" />
<input type="submit" value="submit"></input>
</form>

<form action="window.history.back()">
<a href="javascript:history.back()">Go Back</a>
<br>
</form>


</center>
</body>
</html>