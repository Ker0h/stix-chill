import static org.junit.Assert.assertEquals;

import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//Tests the percentage regex by using the static method checkPercentage
public class WatchedTest {

    @Test
    public void testWatchedCheckPercentageWithCorrectFormat(){
        //Act
        boolean returnValue = Watched.checkPercentage("80%");
        //Assert
        Assert.assertTrue(returnValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWatchedCheckPercentageWithIncorrectFormat(){
        //Act
        boolean returnValue = Watched.checkPercentage("500");
    }
}
