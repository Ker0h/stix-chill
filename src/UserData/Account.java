package UserData;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int subscriberNumber;
    private String name;
    private String streetName;
    private String houseNumber;
    private String city;
    private List profiles;

    public Account(int subscriberNumber, String name, String streetName, String houseNumber, String city) {
        this.subscriberNumber = subscriberNumber;
        this.name = name;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.profiles = new ArrayList<Profile>();
    }

    public Account(int subscriberNumber, String name, String streetName, String houseNumber, String city, List profiles) {
        this.subscriberNumber = subscriberNumber;
        this.name = name;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.profiles = profiles;
    }

    public int getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(int subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getProfiles() {
        return profiles;
    }

    public void setProfiles(List profiles) {
        this.profiles = profiles;
    }

    public void addProfile(Profile profile){
        this.profiles.add(profile);
    }
}
