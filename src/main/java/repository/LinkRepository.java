package repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import db.MongoDB;
import helpers.Functions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkRepository {

    private static final Logger logger = LoggerFactory.getLogger(LinkRepository.class);
    private DBCollection collection;

    public LinkRepository() {
        //FIXME: connect in constructer??
        //Connect to DB
        //collection = MongoDB.connect();
        //Random random = new Random(4L);
        //Link link2 = new Link(random.ints().toString(), "http://graphql.org/learn/", "The official docks");
        //DBObject linkObj2 = Functions.toDBObject(link2);
        //collection.insert(linkObj2);
    }

    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        try{
            collection = MongoDB.connect();
            List<DBObject> cursor = collection.find().toArray();
            for(DBObject obj : cursor){
                ObjectId id = new ObjectId(obj.get("_id").toString());
                Link link = new Link(id,obj.get("url").toString(), obj.get("description").toString());
                links.add(link);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error when fetching links: " + e.getMessage());
        }
        return links;
    }

    public void saveLink(Link link) {
        try{
            //Insert a new Link.
            ObjectId id = new ObjectId();
            link.set_id(id); // New ID for the new Link.
            logger.info("Saving Link to DB: " + link.toString());
            DBObject linkObject = Functions.toDBObject(link);
            collection.insert(linkObject);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Error while saving to DB: " + e.getMessage());
        }
    }
}