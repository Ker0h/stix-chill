package UserData;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String subscriberNumber;
    private String name;
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;
    private List profiles;

    public Account(String subscriberNumber, String name, String streetName, String houseNumber, String postalCode, String city) {
        this.subscriberNumber = subscriberNumber;
        this.name = name;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.profiles = new ArrayList<Profile>();
    }

    public Account(String subscriberNumber, String name, String streetName, String houseNumber, String postalCode, String city, List profiles) {
        this.subscriberNumber = subscriberNumber;
        this.name = name;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.profiles = profiles;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

//    public static List<Account> getSingleProfileAccounts(){
//        List<Account> singleProfileAccounts = new ArrayList<Account>();
//        for(Account a: allAccounts){
//            if(a.getProfiles().size() == 1){
//                singleProfileAccounts.add(a);
//            }
//        }
//        return singleProfileAccounts;
//    }

    @Override
    public String toString() {
        return "Account{" +
                "subscriberNumber=" + subscriberNumber +
                ", name='" + name + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
