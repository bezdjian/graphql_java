package helpers;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import pojo.Link;
import pojo.Person;

public class Functions {
    public static DBObject toDBObject(Link link) {
        return new BasicDBObject("_id", link.get_id())
                .append("url", link.getUrl())
                .append("description", link.getDescription());
    }

    public static DBObject toDBObject(Person person) {
        return new BasicDBObject("_id", person.get_id())
                .append("name", person.getName())
                .append("job", person.getJob())
                .append("address", person.getAddress());
    }
}