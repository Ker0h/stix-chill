import UserData.*;
import Watchables.*;
import GUI.*;
import DatabaseConnections.*;

import javax.swing.*;

public class Starter {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello Bitches");

        Account jop = new Account(110110, "Jop van Wijnen", "Stratenstraat", "1", "4678AB", "Breda");
        Account stijn = new Account(123456, "Stijn van Veen", "Wegenweg", "33", "5690BN", "Bergen op Zoom");
        Account yannick = new Account(987654, "Yannick Willems", "Liesbospark", "28", "4813HV", "Breda");

        Profile jop1 = new Profile("Jop1", "24-11-1995", jop);
        Profile stijnRestricted = new Profile("Stijn (Restricted)", "13-01-2002", stijn);
        Profile ywillems = new Profile("ywillems", "29-09-1996", yannick);

        Series sherlock = new Series("Sherlock", "English", "Detective", 13);

        Episode baskervilleHounds = new Episode(1234, "The Hounds of Baskerville", "01:30:00", "S01E01", sherlock);
        Episode blackBride = new Episode(4567, "The Black Bride", "02:00:00", "S01E02", sherlock);

        System.out.println(ywillems.getDateOfBirth());

        SwingUtilities.invokeLater(new UserInterface());
    }
}
