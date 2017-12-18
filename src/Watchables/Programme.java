package Watchables;

import java.sql.Time;

public class Programme {
    private int programmeID;
    private String title;
    private Time duration;

    public Programme(int programmeID, String title, Time duration) {
        this.programmeID = programmeID;
        this.title = title;
        this.duration = duration;
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
