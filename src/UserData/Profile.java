package UserData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profile {
    private String profileName;
    private Date dateOfBirth;
    private Account account;
    private List watched;

    public Profile(String profileName, Date dateOfBirth, Account account) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        this.watched = new ArrayList();
    }

    public Profile(String profileName, Date dateOfBirth, Account account, List watched) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
