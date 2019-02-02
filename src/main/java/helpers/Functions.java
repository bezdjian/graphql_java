package helpers;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import pojo.Link;

public class Functions {
    public static DBObject toDBObject(Link link) {
        return new BasicDBObject("_id", "999")
                .append("url", link.getUrl())
                .append("description", link.getDescription());
    }
}