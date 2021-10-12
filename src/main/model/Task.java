package model;

public class Task implements Item {
    private String name;
    private Date date;
    private String desc;

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
