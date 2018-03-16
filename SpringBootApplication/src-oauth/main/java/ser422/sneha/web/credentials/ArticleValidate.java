package ser422.sneha.web.credentials;

import ser422.sneha.web.dao.IArticleDAO;
import ser422.sneha.web.datastore.ArticleStore;
import ser422.sneha.web.factory.ArticleDAOFactory;
import ser422.sneha.web.model.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticleValidate {

    public static boolean articleFormValidate(String articleID, String articleTitle, String articleContent, String accessSpecifier,
                                           String reporterId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        //String reporterID = reporterId.trim().toUpperCase();
        System.out.println("inside article validate method");
        System.out.println("ArticleID is "+articleID);
        articleID = articleID.trim();
        if(articleID.isEmpty()){

            System.out.println("validating article id");

            httpServletResponse.setHeader("invalid", " ArticleID should not be empty");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        Article article;
        try{
            article =  articleDAO.getArticleByID(articleID);
        }catch (IllegalArgumentException e){
            article = null;
        }
        if(article != null){
            httpServletResponse.setHeader("invalid", " ArticleID should be unique. \n This ID is assigned to another article");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        if(articleTitle.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", " Title must be given for the article");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        if(articleContent.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", " Content cannot be empty");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        if(accessSpecifier==null){
            httpServletResponse.setHeader("invalid", " Select either public or private");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        if(reporterId.trim().isEmpty()){
            httpServletResponse.setHeader("invalid", " reporter id should match to ur emailid");
            httpServletRequest.getRequestDispatcher("ArticleForm.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }
}
