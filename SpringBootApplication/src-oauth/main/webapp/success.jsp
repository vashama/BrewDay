<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

    <%
    String name = (String) request.getSession().getAttribute("name");
            if(name == null){
                response.setHeader("invalid", "Please Log in..Cannot access without Login");
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
           String UpdateMessage = response.getHeader("updated");
        String errorMessage = response.getHeader("valid");
        errorMessage = errorMessage == null ? "" : errorMessage;
                UpdateMessage = UpdateMessage == null ? "" : UpdateMessage;

    %>

    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>home page</title>
    </head>

    <body style ="background-color:F0E0B0">

    <h1 style="color:black;">
        <%= errorMessage %>
    </h1>

     <h1 style="color:black;">
            <%= UpdateMessage %>
        </h1>


    <center>
    <form action="window.history.back()">


    <a href="javascript:history.back()">Go Back</a>

    </form>
    </center>