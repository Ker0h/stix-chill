package Watchables;

import java.sql.Time;

public abstract class Programme {
    private int programmeID;
    private String title;
    private String duration;

    public Programme(int programmeID, String title, String duration) {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
