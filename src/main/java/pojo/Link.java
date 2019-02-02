package pojo;

import org.bson.types.ObjectId;

public class Link {

    private ObjectId _id;
    private String url;
    private String description;

    //Used in getAllLinks();
    public Link(ObjectId _id, String url, String description) {
        this._id = _id;
        this.url = url;
        this.description = description;
    }

    //Used in createLink();
    public Link(String url, String description) {
        this._id = new ObjectId();
        this.url = url;
        this.description = description;
    }

    public void set_id(ObjectId id){
        this._id = id;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Link{" +
                "_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}