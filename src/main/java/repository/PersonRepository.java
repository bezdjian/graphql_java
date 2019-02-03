package repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import helpers.Functions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);
    private DBCollection collection;

    public PersonRepository(DBCollection collection) {
        System.out.println("PersonRepository Collection: " + collection.getName());
        this.collection = collection;
    }

    public List<Person> getAllPersons(String name) {
        List<Person> allPersons = new ArrayList<>();
        try{
            BasicDBObject query = new BasicDBObject();
            if(name != null && !name.isEmpty()){
                logger.info("Adding " + name + " in the query");
                query.append("name", name);
            }
            List<DBObject> cursor = collection.find(query).toArray();
            for(DBObject obj : cursor){
                ObjectId id = new ObjectId(obj.get("_id").toString());
                Person person = new Person(id, obj.get("name").toString(), obj.get("job").toString(), obj.get("address").toString());
                allPersons.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error when fetching person: " + e.getMessage());
        }
        return allPersons;
    }

    public void savePerson(Person person) {
        try{
            //Insert a new Link.
            ObjectId id = new ObjectId();
            person.set_id(id); // New ID for the new Person.
            DBObject personObject = Functions.toDBObject(person);
            collection.insert(personObject);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Error while saving to DB: " + e.getMessage());
        }
    }
}