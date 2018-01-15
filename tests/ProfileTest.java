import static org.junit.Assert.assertEquals;

import UserData.Account;
import UserData.Profile;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//Tests the constructor and setter for the use of a correct date format
public class ProfileTest {

    @Test
    public void testProfileConstructorWithCorrectFormat(){
        //Arrange
        Account a = new Account("2128086", "Test", "Lovensdijkstraat", "LA", "4888 LC", "Breda");
        //Act
        Profile p = new Profile("Test", "1996-09-29", a);
        //Assert
        boolean returnValue = (p.getDateOfBirth().equals("1996-09-29"));
        Assert.assertTrue(returnValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProfileConstructorWithIncorrectFormat(){
        //Arrange
        Account a = new Account("2128086", "Test", "Lovensdijkstraat", "LA", "4888 LC", "Breda");
        //Act
        Profile p = new Profile("Test", "29-09-1996", a);
    }

    @Test
    public void testSetDateOfBirthWithCorrectFormat(){
        //Arrange
        Account a = new Account("2128086", "Test", "Lovensdijkstraat", "LA", "4888 LC", "Breda");
        //Act
        Profile p = new Profile("Test", "1998-07-08", a);
        p.setDateOfBirth("1996-09-29");
        //Assert
        boolean returnValue = (p.getDateOfBirth().equals("1996-09-29"));
        Assert.assertTrue(returnValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithIncorrectFormat(){
        //Arrange
        Account a = new Account("2128086", "Test", "Lovensdijkstraat", "LA", "4888 LC", "Breda");
        //Act
        Profile p = new Profile("Test", "1998-07-08", a);
        p.setDateOfBirth("29-09-1996");
    }
}
