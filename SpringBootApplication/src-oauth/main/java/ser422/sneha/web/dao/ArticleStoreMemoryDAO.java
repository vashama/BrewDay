package ser422.sneha.web.dao;

import ser422.sneha.web.datastore.ArticleStore;
import ser422.sneha.web.model.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleStoreMemoryDAO implements IArticleDAO{

    public void addArticle(Article article){
        synchronized (ArticleStore.articleMap) {
            ArticleStore.getArticleMap().put(article.getArticleID(), article);
        }
    }
    public void updateArticle(Article article){
        synchronized (ArticleStore.articleMap) {
            ArticleStore.getArticleMap().put(article.getArticleID(), article);
        }
    }

    // To get all public articles
    public List<Article> getAllPublicArticles() {
        List<Article> articles = new ArrayList<>();
        for (Map.Entry<String, Article> article : ArticleStore.getArticleMap().entrySet()) {
            if (article.getValue().getAccessSpecifier().equals("public")) {
                articles.add(article.getValue());
            }
        }
        return articles;
    }

    // To get all private articles

    public List<Article> getAllPrivateArticles() {
        List<Article> articles = new ArrayList<>();

        for (Map.Entry<String, Article> article : ArticleStore.getArticleMap().entrySet()) {

            if (article.getValue().getAccessSpecifier().equals("private")) {
                articles.add(article.getValue());
            }
        }

        return articles;
    }

    // to get all reporter articles.[public + his articles(private & public)]

    public List<Article> getAllReporterArticles(String emailID) {
        List<Article> articles = new ArrayList<>();
        //String emailID = httpServletRequest
        //articles.addAll(getAllPublicArticles());
        for (Map.Entry<String, Article> article : ArticleStore.getArticleMap().entrySet()) {

            if (article.getValue().getReporterId().trim().toUpperCase().equals(emailID)) {
                articles.add(article.getValue());
            }
        }
        return articles;
    }

    //to get all articles from the article collection

    public List<Article> getAllArticles() {
        List<Article> result = getAllPublicArticles();
        result.addAll(getAllPrivateArticles());
        return result;
    }

    //get Articles by ArticleID

    public Article getArticleByID(String articleID) {

        if(ArticleStore.getArticleMap().containsKey(articleID))
            return ArticleStore.getArticleMap().get(articleID);

        throw new IllegalArgumentException("Article Not Found");

    }

    public synchronized boolean deleteArticle(String articleID){
        if(ArticleStore.getArticleMap().containsKey(articleID)){
            ArticleStore.getArticleMap().remove(articleID);
            return true;
        }
        return false;
    }
}
