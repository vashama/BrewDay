package ser422.sneha.web.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ser422.sneha.web.factory.ConnectionFactory;
import org.bson.Document;
import ser422.sneha.web.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleMongoDAO implements IArticleDAO {

    MongoClient connection = null;
    private static final String DB_NAME = "Assignment2";

    public ArticleMongoDAO(String host, String port) {
        connection = getConnection(host,port);
    }

    private MongoClient getConnection(String host, String port){
        return ConnectionFactory.getInstance().getConnection(host, port);
    }

    public void addArticle(Article article){
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        Document basicDBObject = new Document();
        basicDBObject.put("_id", article.getArticleID());
        basicDBObject.put("ArticleID", article.getArticleID());
        basicDBObject.put("ArticleTitle", article.getArticleTitle());
        basicDBObject.put("accessSpecifier", article.getAccessSpecifier());
        basicDBObject.put("ArticleContent", article.getArticleContent());
        basicDBObject.put("ReporterID", article.getReporterId());
        mongoCollection.insertOne(basicDBObject);
    }

    public void updateArticle(Article article){
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        Document basicDBObject = new Document();
        basicDBObject.put("_id", article.getArticleID());
        basicDBObject.put("ArticleID", article.getArticleID());
        basicDBObject.put("ArticleTitle", article.getArticleTitle());
        basicDBObject.put("accessSpecifier", article.getAccessSpecifier());
        basicDBObject.put("ArticleContent", article.getArticleContent());
        basicDBObject.put("ReporterID", article.getReporterId());
        BasicDBObject query = new BasicDBObject();
        query.put("_id", article.getArticleID());
        mongoCollection.findOneAndReplace(query, basicDBObject);
    }

    private Article getArticleFromDBO(Document basicDBObject){
        Article article = new Article();
        article.setArticleID(basicDBObject.get("ArticleID", String.class));
        article.setArticleTitle( basicDBObject.get("ArticleTitle", String.class));
        article.setAccessSpecifier( basicDBObject.get("accessSpecifier", String.class));
        article.setArticleContent( basicDBObject.get("ArticleContent", String.class));
        article.setReporterId( basicDBObject.get("ReporterID", String.class));
        return article;
    }

    public List<Article> getAllPublicArticles(){
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        query.put("accessSpecifier", "public");
        FindIterable findIterable = mongoCollection.find(query);
        MongoCursor cursor = findIterable.iterator();
        List<Article> articles = new ArrayList<>();
        while (cursor.hasNext()){
            articles.add(getArticleFromDBO((Document)cursor.next()));
        }
        return articles;
    }

    public List<Article> getAllPrivateArticles(){
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        query.put("accessSpecifier", "private");
        FindIterable findIterable = mongoCollection.find(query);
        MongoCursor cursor = findIterable.iterator();
        List<Article> articles = new ArrayList<>();
        while (cursor.hasNext()){
            articles.add(getArticleFromDBO((Document)cursor.next()));
        }
        return articles;
    }

    public List<Article> getAllReporterArticles(String emailID){
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        query.put("ReporterID", emailID);
        FindIterable findIterable = mongoCollection.find(query);
        MongoCursor cursor = findIterable.iterator();
        List<Article> articles = new ArrayList<>();
        while (cursor.hasNext()){
            articles.add(getArticleFromDBO((Document)cursor.next()));
        }
        return articles;
    }

    public List<Article> getAllArticles(){
        List<Article> articles = getAllPrivateArticles();
        articles.addAll(getAllPublicArticles());
        return articles;
    }

    public Article getArticleByID(String articleID){
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        query.put("_id", articleID);
        Document document = (Document) mongoCollection.find(query).first();
        if(document == null){
            return null;
        }
        return getArticleFromDBO( document);
    }

    public  boolean deleteArticle(String articleID){
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Article");
        query.put("_id", articleID);
        Document document = (Document) mongoCollection.findOneAndDelete(query);
        if(document == null){
            return false;
        }
        Article article = getArticleFromDBO(document);
        return true;
    }
}
