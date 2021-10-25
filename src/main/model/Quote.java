package model;

import org.json.JSONObject;
import persistence.Writable;

// Quote with its contents and an author
public class Quote implements Writable {
    private String quote;
    private String author;

    // MODIFIES: this
    // EFFECTS:  makes quote with given text and author
    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("quote", quote);
        object.put("author", author);
        return object;
    }
}
