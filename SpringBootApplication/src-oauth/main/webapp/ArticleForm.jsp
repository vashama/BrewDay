<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

    <%
        String errorMessage = response.getHeader("invalid");
        errorMessage = errorMessage == null ? "" : errorMessage;
        String name = (String) request.getSession().getAttribute("name");
        if(name == null){
            response.setHeader("invalid", "Please Log in..Cannot access without Login");
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }

 %>

<html lang="en">


<head>
    <meta charset="UTF-8">
    <title>home page</title>
</head>

<h1 style="color:red;">
    <%= errorMessage %>
</h1>

<body style ="background-color:F0E0B0">


<center>
<form method="post" action="/lab2task2/ArticleServlet">

<H1> Write your article here </H1>
<br>
<br>

Article ID: <br>
<input type="text" name="ArticleID" placeholder="ID0123456789" maxlength="12" value="" ><br><br>


Article title : <br>
<input type = "text" name="Article_title" value=""><br><br>

ARTICLE-CONTENT:<br>
<textarea rows="20" cols="100" name="Article" value="" >
</textarea><br><br>

Article visibility :<br>
<input type="radio" name="access_specifier" value= "public">public</input>

<input type="radio" name="access_specifier" value="private">private</input>
<br><br>

<input type="reset" value="RESET">
<form method="post" action="/lab2task2/ArticleServlet">
<input type="submit" value="PUBLISH" ></form>


</form>
</body>
</html>