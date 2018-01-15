package UserData;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String profileName;
    private String dateOfBirth;
    private Account account;
    private List watched;

    public Profile(String profileName, String dateOfBirth, Account account) {
        if(dateOfBirth.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            this.profileName = profileName;
            this.dateOfBirth = dateOfBirth;
            this.account = account;
            this.watched = new ArrayList<Watched>();
        }else{
            throw new IllegalArgumentException();
        }
    }

    public Profile(String profileName, String dateOfBirth, Account account, List watched) {
        if(dateOfBirth.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            this.profileName = profileName;
            this.dateOfBirth = dateOfBirth;
            this.account = account;
            this.watched = watched;
        }else{
            throw new IllegalArgumentException();
        }
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
        if(dateOfBirth.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            this.dateOfBirth = dateOfBirth;
        }else{
            throw new IllegalArgumentException();
        }
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

    @Override
    public String toString() {
        return "Profile{" +
                "profileName='" + profileName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", account=" + account.getSubscriberNumber() +
                ", watched=" + watched +
                '}';
    }
}
