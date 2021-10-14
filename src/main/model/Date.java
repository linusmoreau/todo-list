package model;

public class Date {
    private boolean isSet;
    private int year;
    private int month;
    private int day;

    // MODIFIES: this
    // EFFECTS:  makes placeholder date object with date not set
    public Date() {
        isSet = false;
    }

    // REQUIRES: year, month, and day represent a possible date
    // MODIFIES: this
    // EFFECTS:  makes date object with date set
    public Date(int year, int month, int day) {
        setDate(year, month, day);
    }

    // REQUIRES: year, month, and day represent a possible date
    // MODIFIES: this
    // EFFECTS: sets the year, month, and day for a day
    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        isSet = true;
    }

    public boolean isSet() {
        return isSet;
    }

    // REQUIRES: day is set
    // EFFECTS: returns day
    public int getDay() {
        return day;
    }

    // REQUIRES: month is set
    // EFFECTS: returns month
    public int getMonth() {
        return month;
    }

    // REQUIRES: year is set
    // EFFECTS: returns year
    public int getYear() {
        return year;
    }

    // EFFECTS: returns a year-month-day representation of date or empty string if date not set
    public String toString() {
        if (isSet()) {
            String day = Integer.toString(getDay());
            if (day.length() == 1) {
                day = "0" + day;
            }
            String month = Integer.toString(getMonth());
            if (month.length() == 1) {
                month = "0" + month;
            }
            String year = Integer.toString(getYear());
            return year + "-" + month + "-" + day;
        } else {
            return "";
        }
    }

    // EFFECTS: returns an integer which can be compared with other dates for ordering
    public int comparator() {
        return getYear() * 10000 + getMonth() * 100 + getDay();
    }
}
