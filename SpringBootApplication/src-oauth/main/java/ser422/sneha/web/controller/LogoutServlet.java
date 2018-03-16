package ser422.sneha.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        httpServletRequest.getSession().invalidate();
        //httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);

        httpServletResponse.sendRedirect("/lab2task2/homepageServlet");

    }
}
