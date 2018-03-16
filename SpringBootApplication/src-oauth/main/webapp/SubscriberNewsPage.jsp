<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ser422.sneha.web.model.Article" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="ser422.sneha.web.model.Person" %>;
<%@ page session="true" %>



<%

        Map<String, Article> articleHrefMap1 = (Map) request.getAttribute("articleMap");


        String name = (String) request.getSession().getAttribute("name");
        String email  = (String) request.getSession().getAttribute("email");


        if(articleHrefMap1 == null && name!= null && email!= null){
            response.setHeader("invalid", "You are logged out due to random page access. Login again!");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
        }

        if(articleHrefMap1 == null){
            response.setHeader("invalid", "Cannot access Random Pages without proper access!! Login");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
        }



        if(name == null){
            response.setHeader("invalid", "Please Log in");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
        }

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

<H1> Welcome to NEW news Inc. </H1>

<h2> Hi <%= name %>
</h2>

<h2> Click on the link to read the article </h2>

<h1 style="color:red;">
    <%= errorMessage %>
</h1>

        <% for(Map.Entry<String, Article> entry : articleHrefMap1.entrySet()) { %>
        <form method="post" action="/lab2task2/FavouriteServlet" id="favButtonForm">
        <a href= "<%=entry.getKey() %>"> <%= entry.getValue().getArticleTitle() %> </a>

        <a href="/lab2task2/FavouriteServlet?AddasFavourite=<%=entry.getValue().getArticleID()%>">
        <input type="button" value="ADD TO FAV"/></a>
        </form>

        <form method="post">
        <a href="/lab2task2/FavouriteServlet?DeleteFavourite=<%=entry.getValue().getArticleID()%>">
        <input type="button" value="DELETE FROM FAV"/></a>
        </form>
        <br><br>
    <% } %>


</center>

<center>
<form method="post" action="/lab2task2/LogoutServlet">
    <input type="submit" value="Logout">
</form>
</center>

</body>
</html>


