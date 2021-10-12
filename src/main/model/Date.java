package model;

public class Date {
    private boolean isSet;
    private int year;
    private int month;
    private int day;

    // MODIFIES: this
    // EFFECTS:  makes placeholder date
    public Date() {
        isSet = false;
    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        isSet = true;
    }

    public boolean isSet() {
        return isSet;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
