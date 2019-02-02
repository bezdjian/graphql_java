package pojo;

public class Link {

    //private final String _id;
    private final String url;
    private final String description;

    public Link(String url, String description) {
        //this._id = _id;
        this.url = url;
        this.description = description;
    }

    //public String get_id() {
        //return _id;
    //}

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Link{" +
                //"_id='" + _id + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}