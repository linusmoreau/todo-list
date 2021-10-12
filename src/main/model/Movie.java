package model;

public class Movie implements Item {
    private String name;
    private String bookmark;

    // MODIFIES: this
    // EFFECTS:  makes movie with given name and placeholder bookmark
    public Movie(String name) {
        this.name = name;
        this.bookmark = "";
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
}
