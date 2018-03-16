<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="ser422.sneha.web.model.Article" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<%


            Article article = (Article) request.getAttribute("article");

            String content = article.getArticleContent();
            String articleid = article.getArticleID();

%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>home page</title>
</head>

<body style ="background-color:F0E0B0">

<center><form >

<H1> Welcome to NEW news Inc. </H1>

<h1 style="color:blue;">
    <%= content%>
</h1>

<h1 style="color:blue;">
    <%= articleid%>
</h1>

<a href="javascript:history.back()">Go Back</a>

</form>
</center>

</body>
</html>