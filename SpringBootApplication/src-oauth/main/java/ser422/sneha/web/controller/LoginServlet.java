package ser422.sneha.web.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ser422.sneha.web.pipelines.LoginProcessor.checkUser;

public class LoginServlet extends HttpServlet  {

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        String email = httpServletRequest.getParameter("email") ;
        String password = httpServletRequest.getParameter("password");

        System.out.println("email inside doPost method "+email);
        checkUser(email, password, httpServletRequest, httpServletResponse);

    }
}
