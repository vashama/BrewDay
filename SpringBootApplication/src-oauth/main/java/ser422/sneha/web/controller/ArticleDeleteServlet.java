package ser422.sneha.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ser422.sneha.web.dao.IArticleDAO;
import ser422.sneha.web.factory.ArticleDAOFactory;

public class ArticleDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException, ServletException {

        String name = (String) httpServletRequest.getSession().getAttribute("name");

        if(name == null){
            httpServletResponse.setHeader("invalid","You have no permission to access this page ");
            httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);

        }

        String ID = httpServletRequest.getParameter("aid");
        if(ID == null){
            httpServletResponse.sendError(404, "Article Not Found");
        }

        System.out.println("received url: " + httpServletRequest.getRequestURL().toString());
        System.out.println("received article ID inside delete Servlet is: " + ID);

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        if (articleDAO.deleteArticle(ID)) {
            httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(404, "Article Not Found");
        }
    }

    }

