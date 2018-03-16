package utils;

import ser422.sneha.web.constants.PathPrefixes;
import ser422.sneha.web.model.Article;

public class HrefUtils {

    public static String generateURIForArticleById(Article article, String contextPath) {
        return contextPath + PathPrefixes.GET_ARTICLE_BY_ID_PREFIX + article.getArticleID();
    }

    public static String generateURIForArticleByIDToEdit(Article article, String contextPath){
        return contextPath + PathPrefixes.GET_ARTICLE_BY_ID_PREFIX_TO_EDIT + article.getArticleID();
    }


    // preparing a map which generate hrefs for public articles :

    /*public static void loadPublicHrefs(Map<String, String> hrefMap, String pathPrefix) {

        for(Map.Entry<String, Article> article :  ArticleStore.getArticleMap().entrySet()) {

            if(article.getValue().getAccessSpecifier().equals("public")) {
                hrefMap.put(pathPrefix + article.getValue().getArticleID(), article.getValue().getArticleTitle());
            }
        }
    }*/

}

