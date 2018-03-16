package ser422.sneha.web.controller;

import ser422.sneha.web.credentials.SignUpManager;
import ser422.sneha.web.pipelines.LoginProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


public class SignUpServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        String name = httpServletRequest.getParameter("name");
        String email = httpServletRequest.getParameter("email").trim().toUpperCase();
        String password = httpServletRequest.getParameter("password");
        String userRole = httpServletRequest.getParameter("user");

        boolean isReporter = false;

        SignUpManager.validateSignup(name, email, password, userRole, httpServletRequest, httpServletResponse);

        if(userRole.equals("reporter")) {
            isReporter = true;
        }

        LoginProcessor.addUser(name,email,password,isReporter,httpServletRequest,httpServletResponse);

        Cookie emailCookie = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
        Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));

        httpServletResponse.addCookie(emailCookie);
        httpServletResponse.addCookie(nameCookie);

        //httpServletRequest.getRequestDispatcher("Reporterpage.jsp").forward(httpServletRequest, httpServletResponse);

    }
}


