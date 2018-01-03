package UserData;

import Watchables.Programme;

public class Watched {
    private int percentage;
    private String profileName;
    private int  programmeID;

    public Watched(int percentage, String profileName, int programmeID) {
        this.percentage = percentage;
        this.profileName = profileName;
        this.programmeID = programmeID;
    }

    public Watched(String profileName, int programmeID) {
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

    public int GetProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(int ProgrammeID) {
        this.programmeID = programmeID;
    }

    public void incrementPercentage(){
        this.percentage++;
    }

    public void incrementPercentage(int amount){
        this.percentage += amount;
        if(this.percentage > 100){
            this.percentage = 100;
        }
    }

    @Override
    public String toString() {
        return "Watched{" +
                "percentage=" + percentage +
                ", profileName='" + profileName + '\'' +
                ", programmeID=" + programmeID +
                '}';
    }
}
