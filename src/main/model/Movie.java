package model;

import org.json.JSONObject;
import persistence.Writable;

// Movie with a name and, optionally, a bookmark
public class Movie implements Writable, Item {
    private String name;
    private String bookmark;

    // MODIFIES: this
    // EFFECTS:  makes movie with given name and placeholder bookmark
    public Movie(String name) {
        this.name = name;
        this.bookmark = "";
    }

    // MODIFIES: this
    // EFFECTS:  makes movie with given name and bookmark
    public Movie(String name, String bookmark) {
        this.name = name;
        this.bookmark = bookmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("bookmark", bookmark);
        return object;
    }
}
