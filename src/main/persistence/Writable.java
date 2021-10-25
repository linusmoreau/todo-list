package persistence;

import org.json.JSONObject;

// Interface for objects writable to JSON
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
