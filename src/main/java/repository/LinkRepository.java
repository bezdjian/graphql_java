package repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import helpers.Functions;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import model.Link;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LinkRepository {

    private final DBCollection collection;

    public LinkRepository(DBCollection collection) {
        log.info("LinkRepository Collection: " + collection.getName());
        this.collection = collection;
    }

    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        try {
            List<DBObject> cursor = collection.find().toArray();
            for (DBObject obj : cursor) {
                ObjectId id = new ObjectId(obj.get("_id").toString());
                Link link = new Link(id, obj.get("url").toString(), obj.get("description").toString());
                System.out.println("Adding Link: " + link.toString());
                links.add(link);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error when fetching links: " + e.getMessage());
        }
        return links;
    }

    public void saveLink(Link link) {
        try {
            //Insert a new Link.
            ObjectId id = new ObjectId();
            link.set_id(id); // New ID for the new Link.
            log.info("Saving Link to DB: " + link.toString());
            DBObject linkObject = Functions.toDBObject(link);
            collection.insert(linkObject);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while saving to DB: " + e.getMessage());
        }
    }

    public void deleteLink(String url) {
        BasicDBObject query = new BasicDBObject();
        if (url != null && !url.isEmpty()) {
            query.append("url", url);
            //List<DBObject> link = collection.find(query).toArray();
            collection.remove(query);
        }
    }
}