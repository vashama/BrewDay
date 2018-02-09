<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>


<%

Cookie[] cookies = request.getCookies();
String firstNameCookieValue = "";
String lastNameCookieValue= "";
if(cookies != null){

                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("lab1cookieFirstName")){
                        firstNameCookieValue = cookie.getValue();
                        continue;
                    }
                    if(cookie.getName().equals("lab1cookieLastName")){
                        lastNameCookieValue = cookie.getValue();
                        continue;
                    }
                }
            }
%>
<html lang="en">

<HEAD>

    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <TITLE>A Web Form</TITLE>

</HEAD>
 <%
      String browser = request.getHeader("user-agent");
      if (browser.indexOf("MSIE") == -1 && browser.indexOf("Edge") == -1 && browser.indexOf("Chrome") != -1) {
 %>
<body style="background-color:cyan" >
<%
}
%>

<form method="post" action="/lab1task1/newPerson">

    First name:<br>
    <INPUT TYPE="TEXT" NAME="firstName" VALUE="<%= firstNameCookieValue %>"><BR>

    Last name:<br>
    <INPUT TYPE="TEXT" NAME="lastName" VALUE="<%= lastNameCookieValue %>"><BR>

    Programming languages:<br>
    <INPUT multiple TYPE="TEXT" NAME="Programming languages" VALUE=" " style=" height: 60px; width:300px; "><br>

    Meeting days of the week:<br>
    <INPUT multiple TYPE="TEXT" NAME="week_Days" VALUE=" " style=" height: 60px; width:300px;"><br>

    E-mail:<br>
    <input type="email" name="email"><br><br>

    <INPUT TYPE="submit" Value="submit">

</form>


<h2> use this form to get data from xml file </h2>
<form method="get" action="/lab1task1/getPerson">


    First name:<br>
    <INPUT TYPE="TEXT" NAME="firstName" VALUE=" "><BR>

    Last name:<br>
    <INPUT TYPE="TEXT" NAME="lastName" VALUE=" "><BR>

    Programming languages:<br>
    <INPUT multiple TYPE="TEXT" NAME="Programming languages" VALUE=" " style=" height: 60px; width:300px; "><br>

    Meeting days of the week:<br>
    <INPUT multiple TYPE="TEXT" NAME="week_Days" VALUE=" " style=" height: 60px; width:300px;"><br>

    E-mail:<br>
    <input type="email" name="email"><br><br>

    <INPUT TYPE="submit" Value="Display">


</form>

</body>

</html>
