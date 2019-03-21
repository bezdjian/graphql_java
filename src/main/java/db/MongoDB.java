package db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

public class MongoDB {

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);

    public static DBCollection connect(String collectionName){
        DBCollection collection = null;
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            DB database = mongoClient.getDB("graphql_db");
            collection = database.getCollection(collectionName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("Error accured when connecting to MongoDB: " + e.getMessage());
        }
        return collection;
    }
}
