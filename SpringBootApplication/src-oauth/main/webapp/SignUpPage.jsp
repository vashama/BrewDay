<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

    <%@ page import="java.net.URLEncoder" %>
    <%@ page import="java.net.URLDecoder" %>
<%

    String errorMessage = response.getHeader("invalid");
    errorMessage = errorMessage == null ? "" : errorMessage;

    String name = null;
    String email = null;
    for(Cookie cookie : request.getCookies()){
        if(cookie.getName().equals("name")){
            name = URLDecoder.decode(cookie.getValue(), "UTF-8");
            continue;
        }
         if(cookie.getName().equals("email")){
            email = URLDecoder.decode(cookie.getValue(), "UTF-8");
            continue;
         }
    }
    name = name == null ? "" : name;
    email = email == null ? "" : email;

%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>home page</title>
</head>

<body style ="background-color:F0E0B0">

<h1 style="color:red;">
    <%= errorMessage %>
</h1>

<center><form method="post" action="/lab2task2/SignUpServlet">

<H1> Sign Up to NEW news Inc. </H1>
NAME:
<input type="name" name="name" value="<%=name%>"> <br><br>
EMAIL-ID:
<input type="email" name= "email" value= "<%=email%>"> <br><br>

PASSWORD:
<input type="password" name="password" value=""> <br><br>

SIGN UP AS:<br>

<input type="radio" name="user" value="subscriber">Subscriber<br>
<input type="radio" name="user" value="reporter">Reporter<br><br>

<input type="reset" value="Reset" ><br><br>

<input type="submit" value="Sign Up"><br>

</form>
</center>

<center>
<form method="post" action="/lab2task1/LoginPage.jsp">
<input type="submit" value="Login">
</center>
</form>

</body>
</html>


