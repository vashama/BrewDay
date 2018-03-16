package ser422.sneha.web.dao;

import ser422.sneha.web.model.Article;

import java.util.List;

public interface IArticleDAO {

    void addArticle(Article article);

    void updateArticle(Article article);

    List<Article> getAllPublicArticles();

    List<Article> getAllPrivateArticles();

    List<Article> getAllReporterArticles(String emailID);

    List<Article> getAllArticles();

    Article getArticleByID(String articleID);

    boolean deleteArticle(String articleID);
}
