package Watchables;

import java.sql.Time;

public class Film extends Programme {
    private String genre;
    private String language;
    private int pg;

    public Film(int programmeID, String title, String duration, String genre, String language, int pg) {
        super(programmeID, title, duration);
        this.genre = genre;
        this.language = language;
        this.pg = pg;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    @Override
    public String toString() {
        return "Film{" +
                "genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", pg=" + pg +
                ", title=" + super.getTitle() +
                ", duration=" + super.getDuration() +
                '}';
    }
}
