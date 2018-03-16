package ser422.sneha.web.factory;

import ser422.sneha.web.dao.*;

public class ArticleDAOFactory {

    private static boolean inMemory = true;
    private static String host;
    private static String port;

    public static void initialize(boolean inMemory, String host, String port) {
        if(!inMemory){
            ArticleDAOFactory.inMemory = false;
            ArticleDAOFactory.host = host;
            ArticleDAOFactory.port = port;
        }
    }

    public static IArticleDAO getArticleDAO(){
        if(inMemory)
            return new ArticleStoreMemoryDAO();
        return new ArticleMongoDAO(host,port);

    }
}
