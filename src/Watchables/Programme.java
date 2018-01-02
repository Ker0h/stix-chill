package Watchables;



import UserData.Watched;

import java.util.ArrayList;
import java.util.List;

public abstract class Programme {
    private int programmeID;
    private String title;
    private String duration;
    private List watched;

    public Programme(int programmeID, String title, String duration) {
        this.programmeID = programmeID;
        this.title = title;
        this.duration = duration;
        this.watched = new ArrayList<Watched>();
    }

    public int getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(int programmeID) {
        this.programmeID = programmeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List getWatched() {
        return watched;
    }

    public void setWatched(List watched) {
        this.watched = watched;
    }
    public void addWatched(Watched watched){
        this.watched.add(watched);
    }
}
