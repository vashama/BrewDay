<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="javax.servlet.http.HttpSession" %>
    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.HashMap" %>
    <%@ page import="ser422.sneha.web.model.Article" %>
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="javax.servlet.http.HttpServletResponse" %>
    <%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
    <%@ page import="java.net.URLEncoder" %>
    <%@ page import="java.net.URLDecoder" %>

    <%

        String errorMessage = response.getHeader("invalid");
        errorMessage = errorMessage == null ? "" : errorMessage;
    String email = null;
    for(Cookie cookie : request.getCookies()){
         if(cookie.getName().equals("email")){
            email = URLDecoder.decode(cookie.getValue(),"UTF-8");
            continue;
         }
    }
    email = email == null ? "" : email;
    %>



    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>home page</title>
    </head>

    <body style ="background-color:F0E0B0">

    <center><form method="post" action="/lab2task2/LoginServlet">

    <h2> Hi !! </h2>

    <h1 style="color:red;">
        <%= errorMessage %>
    </h1>

    EMAIL-ID:
    <input type="email" name="email" value="<%=email%>"> <br><br>

    PASSWORD:

    <input type="password" name="password" value=""> <br><br>


    <input type="submit" value="Login" ></center>
    </form>
    </center>
    </body>
    </html>