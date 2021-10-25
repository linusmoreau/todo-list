package model;

import org.json.JSONObject;
import persistence.Writable;

// A task with a short name and possible associated date and description
public class Task implements Comparable, Writable {
    private String name;
    private Date date;
    private String desc;

    // MODIFIES: this
    // EFFECTS:  makes task with given name and placeholder date and description
    public Task(String name) {
        this.name = name;
        date = new Date();
        desc = "";
    }

    // MODIFIES: this
    // EFFECTS:  makes task with given name and date and placeholder description
    public Task(String name, Date date) {
        this.name = name;
        this.date = date;
        desc = "";
    }

    // MODIFIES: this
    // EFFECTS:  makes task with given name, date, and description
    public Task(String name, Date date, String desc) {
        this.name = name;
        this.date = date;
        this.desc = desc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns > 0 if this date is after the given task's date
    //          returns == 0 if they have the same date
    //          returns < 0 if this date is before the given task's date
    @Override
    public int compareTo(Object o) {
        return (Integer.compare(this.getDate().comparator(), ((Task) o).getDate().comparator()));
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("date", date.toJson());
        object.put("desc", desc);
        return object;
    }
}
