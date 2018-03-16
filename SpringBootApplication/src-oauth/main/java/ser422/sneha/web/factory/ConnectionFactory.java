package ser422.sneha.web.factory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() { }

    public MongoClient getConnection(String host, String port){
        return new MongoClient(new MongoClientURI("mongodb://" + host + ":" + port));

    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}