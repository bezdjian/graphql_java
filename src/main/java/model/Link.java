package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Link {

    private ObjectId _id;
    private String url;
    private String description;

    //Used in createLink();
    public Link(String url, String description) {
        this._id = new ObjectId();
        this.url = url;
        this.description = description;
    }
}