package servlet;

public class Movie {
    private int id;
    private String name;
    private String year;
    private String date;

    public Movie(int id, String name, String year, String date) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }
}
