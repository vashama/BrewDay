package ser422.sneha.web.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ser422.sneha.web.factory.ConnectionFactory;
import org.bson.Document;
import ser422.sneha.web.model.Person;
import ser422.sneha.web.model.UserType;

import java.util.List;

public class PersonMongoDAO implements IPersonDAO {

    MongoClient connection = null;
    private static final String DB_NAME = "Assignment2";

    public PersonMongoDAO(String host, String port) {
        connection = getConnection(host, port);
    }

    private MongoClient getConnection(String host, String port){
       return ConnectionFactory.getInstance().getConnection(host, port);
    }

    public Person getPerson(String email) {
        BasicDBObject query = new BasicDBObject();
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Person");
        query.put("email", email);
        Document basicDBObject = (Document) mongoCollection.find(query).first();
        if(basicDBObject == null)
            return null;
        Person person = new Person();
        person.setName(basicDBObject.get("name", String.class));
        person.setEmail( basicDBObject.get("email", String.class));
        person.setPassword(basicDBObject.get("password", String.class));
        String userRole =  basicDBObject.get("userRole", String.class);
        if(UserType.REPORTER.toString().equals(userRole))
            person.setUserType(UserType.REPORTER);
        else
            person.setUserType(UserType.SUBSCRIBER);
        person.setFavArticleIDs((List<String>) basicDBObject.get("favoriteArticles", List.class));

        return person;
    }

    public void addPerson(Person person) {
        Document basicDBObject = new Document();
        basicDBObject.put("_id", person.getEmail());
        basicDBObject.put("name", person.getName());
        basicDBObject.put("email", person.getEmail());
        basicDBObject.put("password", person.getPassword());
        basicDBObject.put("userRole", person.getUserType().toString());
        basicDBObject.put("favoriteArticles", person.getFavArticleIDs());
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Person");
        mongoCollection.insertOne(basicDBObject);
    }

    public void updatePerson(Person person) {
        Document basicDBObject = new Document();
        basicDBObject.put("_id", person.getEmail());
        basicDBObject.put("name", person.getName());
        basicDBObject.put("email", person.getEmail());
        basicDBObject.put("password", person.getPassword());
        basicDBObject.put("userRole", person.getUserType().toString());
        basicDBObject.put("favoriteArticles", person.getFavArticleIDs());
        MongoDatabase mongoDatabase = connection.getDatabase(DB_NAME);
        MongoCollection mongoCollection = mongoDatabase.getCollection("Person");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", person.getEmail());
        mongoCollection.findOneAndReplace(query, basicDBObject);
    }

}
