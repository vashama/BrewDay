package ser422.sneha.web.pipelines;

import ser422.sneha.web.dao.IArticleDAO;
import ser422.sneha.web.dao.IPersonDAO;
import ser422.sneha.web.factory.ArticleDAOFactory;
import ser422.sneha.web.factory.PersonDAOFactory;
import ser422.sneha.web.model.Article;
import ser422.sneha.web.model.Person;
import utils.HrefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleSelector {

    public static void loadPublicArticleURIs(Map<String, String> hrefMap, String contextPath) {
        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        List<Article> articles = articleDAO.getAllPublicArticles();

        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article, contextPath), article.getArticleTitle());
        }
    }

    public static void loadPublicArticleForReporterURIs(Map<String, Article> hrefMap, String contextPath) {
        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

        List<Article> articles = articleDAO.getAllPublicArticles();

        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article, contextPath), article);
        }
    }

    public static void loadPrivateArticleURI(Map<String,String> hrefMap, String contextPath){

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

        List<Article> articles = articleDAO.getAllPrivateArticles();

        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article.getArticleTitle());
        }
    }

    public static void loadAllArticleURI(Map<String,String> hrefMap, String contextPath){

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

        List<Article> articles = articleDAO.getAllArticles();
        System.out.println("inside load all articles");
        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article.getArticleTitle());
        }
        System.out.println("OUTSIDE load all articles");

    }

    public static void loadReporterArticleURI(Map<String,Article> hrefMap, String contextPath,String emailID){

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

        List<Article> articles = articleDAO.getAllReporterArticles(emailID);
        System.out.println("inside load all reporter articles");
        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article);
        }
    }

   public static void loadReporterArticleURIToEdit(Map<String,String> hrefMap, String contextPath,String emailID){

       IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();

       List<Article> articles = articleDAO.getAllReporterArticles(emailID);
        System.out.println("inside load all reporter articles to edit");
        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article.getArticleTitle());
        }
    }

    public static void loadAllFavArticleURI(Map<String,String> hrefMap, String contextPath, String email){

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();

        Person person = personDAO.getPerson(email);

        List<Article> articles = new ArrayList<>();

        for(String articleID : person.getFavArticleIDs()){
            articles.add(articleDAO.getArticleByID(articleID));
        }

        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article.getArticleTitle());
        }
    }

    public static void loadAllFavAndOtherArticleURI(Map<String, Article> hrefMap, String contextPath, String email){

        IArticleDAO articleDAO = ArticleDAOFactory.getArticleDAO();
        IPersonDAO personDAO = PersonDAOFactory.getPersonDAO();

        Person person = personDAO.getPerson(email);

        List<Article> articles = new ArrayList<>();

        articles.addAll(articleDAO.getAllArticles());
        List<Article> favoriteArticles = new ArrayList<>();

        for(String articleID : person.getFavArticleIDs()){
            Article article;
            try{
                article =  articleDAO.getArticleByID(articleID);
                favoriteArticles.add(article);
                articles.remove(article);
            }catch (IllegalArgumentException e){
                article = null;
            }
        }

        for(Article article : favoriteArticles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article);
        }

        for(Article article : articles){
            hrefMap.put(HrefUtils.generateURIForArticleById(article,contextPath),article);
        }
    }
}
