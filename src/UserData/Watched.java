package UserData;

import Watchables.Programme;

public class Watched {
    private int percentage;
    private String profileName;
    private String programmeID;

    public Watched(int percentage, String profileName, String programmeID) {
        this.percentage = percentage;
        this.profileName = profileName;
        this.programmeID = programmeID;
    }

    public Watched(String profileName, String programmeID) {
        this.percentage = 0;
        this.profileName = profileName;
        this.programmeID = programmeID;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getprofileName() {
        return profileName;
    }

    public void setProfile(String profileName) {
        this.profileName = profileName;
    }

    public String GetProgrammeID() {
        return programmeID;
    }

    public void setProgramme(Programme programme) {
        this.programmeID = programmeID;
    }

    public void incrementPercentage(){
        this.percentage++;
    }

    public void incrementPercentage(int amount){
        this.percentage += amount;
    }
}
