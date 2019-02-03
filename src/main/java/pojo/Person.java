package pojo;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

public class Person extends BasicDBObject {

    private ObjectId _id;
    private String name;
    private String job;

    private String address;

    public Person(ObjectId _id, String name, String job, String address) {
        this._id = _id;
        this.name = name;
        this.job = job;
        this.address = address;
    }

    public Person(String name, String job, String address){
        this.name = name;
        this.job = job;
        this.address = address;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
