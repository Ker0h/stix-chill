package UserData;

import Watchables.Programme;

public class Watched {
    private int percentage;
    private Profile profile;
    private Programme programme;

    public Watched(int percentage, Profile profile, Programme programme) {
        this.percentage = percentage;
        this.profile = profile;
        this.programme = programme;
    }

    public Watched(Profile profile, Programme programme) {
        this.percentage = 0;
        this.profile = profile;
        this.programme = programme;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public void incrementPercentage(){
        this.percentage++;
    }

    public void incrementPercentage(int amount){
        this.percentage += amount;
    }
}
