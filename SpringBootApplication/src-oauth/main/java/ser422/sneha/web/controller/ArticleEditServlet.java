package ser422.sneha.web.controller;


import ser422.sneha.web.dao.IArticleDAO;
import ser422.sneha.web.factory.ArticleDAOFactory;
import ser422.sneha.web.model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleEditServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws IOException, ServletException{
        String ID = httpServletRequest.getParameter("aid");
        if(ID == null){
            httpServletResponse.sendError(404, "Article Not Found");
        }

        try {
            IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
            Article article = articleDAO.getArticleByID(ID);
            if(article == null)
                throw new IllegalArgumentException();
            String articleID = article.getArticleID();
            String articlecontent = article.getArticleContent();

            httpServletRequest.getSession().setAttribute("articleID",articleID);
            httpServletRequest.getSession().setAttribute("articlecontent",articlecontent);

            httpServletRequest.getRequestDispatcher("EditArticle.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(404, "Article Not Found");
        }

    }

    }
