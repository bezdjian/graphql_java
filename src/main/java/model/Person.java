package model;

import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Person extends BasicDBObject {

    private ObjectId _id;
    private String name;
    private String job;
    private String address;

    public Person(String name, String job, String address) {
        this.name = name;
        this.job = job;
        this.address = address;
    }
}
