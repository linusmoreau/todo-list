package model;

public class Task {
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
}
