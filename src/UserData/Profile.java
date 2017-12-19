package UserData;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String profileName;
    private String dateOfBirth;
    private Account account;
    private List watched;

    public Profile(String profileName, String dateOfBirth, Account account) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        this.watched = new ArrayList<Watched>();
    }

    public Profile(String profileName, String dateOfBirth, Account account, List watched) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        this.watched = watched;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
