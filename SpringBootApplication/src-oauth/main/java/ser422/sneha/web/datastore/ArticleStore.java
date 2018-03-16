package ser422.sneha.web.datastore;

import ser422.sneha.web.model.Article;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleStore {

    public static Map<String, Article> articleMap = new LinkedHashMap<>();

    static {
        Article article = new Article("SAMPLEID1", "Title-1", "This is a sample article content 1\n\nstory1 ", "public","reporter1");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID2", "Title-2", "This is a sample article content 2\n\nstory2", "public","reporter2");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID3", "Title-3", "This is a sample article content 3\n\nstory3", "private","reporter3");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID4", "Title-4", "This is a sample article content 4\n\nstory4", "public","reporter4");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID5", "Title-5", "This is a sample article content 5\n\nstory5", "public","reporter5");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID6", "Title-6", "This is a sample article content 6\n\nstory6", "public","reporter6");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID7", "Title-7", "This is a sample article content 7\n\nstory7", "public","reporter7");
        articleMap.put(article.getArticleID(), article);
        article = new Article("SAMPLEID8", "Title-8", "This is a sample article content 8\n\nstory7", "public","reporter8");
        articleMap.put(article.getArticleID(), article);
    }

    public static Map<String, Article> getArticleMap() {
        return articleMap;
    }

    public static void setArticleMap(Map<String, Article> articleMap) {
        ArticleStore.articleMap = articleMap;
    }

}
