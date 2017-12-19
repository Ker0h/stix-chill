import UserData.*;
import Watchables.*;
import GUI.*;
import DatabaseConnections.*;

import java.text.SimpleDateFormat;

public class Starter {
    public static void main(String args[]) throws Exception {
        System.out.println("Hello Bitches");

        Account jop = new Account(110110, "Jop van Wijnen", "Stratenstraat", "1", "4678AB", "Breda");
        Account stijn = new Account(123456, "Stijn van Veen", "Wegenweg", "33", "5690BN", "Bergen op Zoom");
        Account yannick = new Account(987654, "Yannick Willems", "Liesbospark", "28", "4813HV", "Breda");

        Profile jop1 = new Profile("Jop1", "24-11-1995", jop);
        Profile stijnRestricted = new Profile("Stijn Restricted", "13-01-2002", stijn);
        Profile ywillems = new Profile("ywillems", "29-09-1996", yannick);

        System.out.println(ywillems.getDateOfBirth());

    }
}
