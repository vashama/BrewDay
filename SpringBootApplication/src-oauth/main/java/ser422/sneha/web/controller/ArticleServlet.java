package ser422.sneha.web.controller;

import ser422.sneha.web.dao.IArticleDAO;
import ser422.sneha.web.factory.ArticleDAOFactory;
import ser422.sneha.web.model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ser422.sneha.web.credentials.ArticleValidate.*;

public class ArticleServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        String methodName = httpServletRequest.getParameter("methodName");
        if(methodName != null && methodName.equalsIgnoreCase("put")){
            doPut(httpServletRequest,httpServletResponse);
        }
        else {
            String articleID = httpServletRequest.getParameter("ArticleID");
            String articleTitle = httpServletRequest.getParameter("Article_title");
            String article = httpServletRequest.getParameter("Article");
            String accessSpecifier = httpServletRequest.getParameter("access_specifier");
            String reporterId = (String) httpServletRequest.getSession().getAttribute("email");
            IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

            if (articleFormValidate(articleID, articleTitle, article, accessSpecifier, reporterId.trim().toUpperCase(), httpServletRequest, httpServletResponse)) {
                articleID = articleID.trim();
                Article article1;
                try{
                    article1 = articleDAO.getArticleByID(articleID);
                }catch (IllegalArgumentException e){
                    article1 = null;
                }
                if(article1 != null){
                    httpServletResponse.sendError(421, "Article with article ID : " + articleID + " already exists");
                    return;
                }


                Article article2 = new Article(articleID, articleTitle, article, accessSpecifier, reporterId);
                articleDAO.addArticle(article2);
                httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);
            }
        }
    }


    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        System.out.println("received url: " + httpServletRequest.getRequestURL().toString());

        String ID = httpServletRequest.getParameter("aid");
        System.out.println("received aid is: " + ID);
        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

        try {
            Article article = articleDAO.getArticleByID(ID);
            if(article.getAccessSpecifier().equals("private")){
                String email = (String) httpServletRequest.getSession().getAttribute("email");
                if(email == null){
                    httpServletResponse.sendError(403, "Access Denied");
                    return;
                }
                String role = (String) httpServletRequest.getSession().getAttribute("role");
                if(role == null){
                    httpServletResponse.sendError(403, "Access Denied");
                    return;
                }
                if(role.equals("REPORTER") && !email.equals(article.getReporterId())){
                    httpServletResponse.sendError(403, "Access Denied");
                    return;
                }
            }
            httpServletRequest.setAttribute("article", article);
            httpServletRequest.getRequestDispatcher("ArticleContent.jsp").forward(httpServletRequest, httpServletResponse);

        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(404, "Article Not Found");
        }
    }


    @Override
    public void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        System.out.println("Inside DOPUT of Article Servlet");

        String ID = httpServletRequest.getParameter("aid");
        String content = httpServletRequest.getParameter("articlecontent");

        System.out.println("Article ID : " + ID + ", Article content is: " + content);

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        try {
            Article article = articleDAO.getArticleByID(ID);
            article.setArticleContent(content);
            articleDAO.updateArticle(article);
            httpServletResponse.setHeader("valid", "Article Content updated ");
            httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(404, "Article Not Found");
        }
    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        System.out.println("received url: " + httpServletRequest.getRequestURL().toString());
        String ID = httpServletRequest.getParameter("aid");
        System.out.println("received article ID inside put method is: " + ID);

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        if (articleDAO.deleteArticle(ID)) {
            httpServletRequest.getRequestDispatcher("/homepageServlet").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(404, "Article Not Found");
        }
    }
}
