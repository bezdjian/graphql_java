package repository;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import helpers.Functions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Link;

import java.util.ArrayList;
import java.util.List;

public class LinkRepository {

    private static final Logger logger = LoggerFactory.getLogger(LinkRepository.class);
    private DBCollection collection;

    public LinkRepository(DBCollection collection) {
        System.out.println("LinkRepository Collection: " + collection.getName());
        this.collection = collection;
    }

    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        try{
            List<DBObject> cursor = collection.find().toArray();
            for(DBObject obj : cursor){
                ObjectId id = new ObjectId(obj.get("_id").toString());
                Link link = new Link(id,obj.get("url").toString(), obj.get("description").toString());
                System.out.println("Adding Link: " + link.toString());
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