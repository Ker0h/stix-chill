package Watchables;

public class Episode extends Programme {
    private String season;
    private Series series;

    public Episode(int programmeID, String title, String duration, String season, Series series) {
        super(programmeID, title, duration);
        this.season = season;
        this.series = series;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
