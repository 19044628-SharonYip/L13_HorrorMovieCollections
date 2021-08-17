package sg.edu.rp.c346.id19044628.l13_horrormoviecollections;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String name;
    private int year;
    private String description;
    private int pgRate;
    private int stars;


    public Movie(int id, String name,int year, String description, int pgRate, int stars) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.pgRate = pgRate;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPgRate() {
        return pgRate;
    }

    public void setPgRate(int pgRate) {
        this.pgRate = pgRate;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        /*return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pgRate=" + pgRate +
                ", stars=" + stars +
                '}';*/

        String starString="";
        for (int i=0;i<stars;i++)
        {
            starString+="*";
        }
        return starString;
    }
}
