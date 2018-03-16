<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="ser422.sneha.web.model.Article" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page session="true" %>



<%
Map<String, Article> reporterArticleMap = (Map) request.getAttribute("reporterArticleMap");
Map<String, String> uriMap = (Map) request.getAttribute("publicArticleMap");
String name = (String) request.getSession().getAttribute("name");

if(reporterArticleMap == null){
     response.setHeader("invalid", "Cannot access as Reporter without Login as Reporter. Please Log in");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
     return;
}


if(uriMap == null){
     response.setHeader("invalid", "Cannot access as pubic uris - null");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
     return;
}

if(name == null){
    response.setHeader("invalid", "Please Log in");
            request.getRequestDispatcher("/homepageServlet").forward(request, response);
    return;
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

<% for(Map.Entry<String, String> entry : uriMap.entrySet()) { %>

        <a href= "<%=entry.getKey()%>"> <%=entry.getValue()%> </a>
        <br><br>
 <% } %>

 <% for(Map.Entry<String, Article> entry : reporterArticleMap.entrySet()) { %>

         <a href= "<%=entry.getKey()%>"> <%=entry.getValue().getArticleTitle()%> </a><br>

                <a href="/lab2task2/ArticleEditServlet?aid=<%=entry.getValue().getArticleID()%>">
                <input type="submit" value="EDIT"></a>

                <a href="/lab2task2/ArticleDeleteServlet?aid=<%=entry.getValue().getArticleID()%>">
                <input type="submit" value="DELETE"></a>

                <br><br>
  <% } %>
<br>
<br>

</center>


<center>
<form method="post" action="/lab2task2/LogoutServlet">
<input type="submit" value="LOGOUT"><br>
</form>
</center>

<center>
<form method="post" action="/lab2task2/ArticleForm.jsp">
    <input type="submit" value="Create new article">
</form>
</center>

</body>
</html>


