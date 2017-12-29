package Watchables;

import java.util.ArrayList;
import java.util.List;

public class Series {
    private String seriesTitle;
    private String language;
    private String genre;
    private int pg;
    private Series isLike;
    private List episodes;

    public Series(String seriesTitle, String language, String genre, int pg, Series isLike) {
        this.seriesTitle = seriesTitle;
        this.language = language;
        this.genre = genre;
        this.pg = pg;
        this.isLike = isLike;
        this.episodes = new ArrayList<Episode>();
    }

    public Series(String seriesTitle, String language, String genre, int pg, Series isLike, List episodes) {
        this.seriesTitle = seriesTitle;
        this.language = language;
        this.genre = genre;
        this.pg = pg;
        this.isLike = isLike;
        this.episodes = episodes;
    }

    public Series(String seriesTitle, String language, String genre, int pg) {
        this.seriesTitle = seriesTitle;
        this.language = language;
        this.genre = genre;
        this.pg = pg;
        this.isLike = null;
        this.episodes = new ArrayList<Episode>();
    }

    public Series(String seriesTitle, String language, String genre, int pg, List episodes) {
        this.seriesTitle = seriesTitle;
        this.language = language;
        this.genre = genre;
        this.pg = pg;
        this.isLike = null;
        this.episodes = episodes;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public Series getIsLike() {
        return isLike;
    }

    public void setIsLike(Series isLike) {
        this.isLike = isLike;
    }

    public List getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List episodes) {
        this.episodes = episodes;
    }

    public void addEpisode(Episode episode){
        this.episodes.add(episode);
    }
}
