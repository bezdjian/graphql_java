package db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.net.UnknownHostException;

@Slf4j
@UtilityClass
public class MongoDB {

    public static DBCollection connect(String collectionName) {
        DBCollection collection = null;
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            DB database = mongoClient.getDB("graphql_db");
            collection = database.getCollection(collectionName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error("Error accured when connecting to MongoDB: " + e.getMessage());
        }
        return collection;
    }
}
