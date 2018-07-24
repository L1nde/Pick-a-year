package ee.skyhigh.l1nde.pickayear;

import java.util.Date;

public class Question {
    private String path;
    private int year;
    private int month;
    private int day;

    public Question(String path, int year, int month, int day) {
        this.path = path;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getPath() {
        return path;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
