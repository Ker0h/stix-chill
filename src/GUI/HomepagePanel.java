package GUI;

import javax.swing.*;
import java.awt.*;

public class HomepagePanel extends JPanel {
    HomepagePanel(){
        super(new BorderLayout());
        JLabel welcomeText = new JLabel("Welcome to Stix & Chill");
        welcomeText.setFont(new Font("Serif", Font.PLAIN, 30));

        this.add(welcomeText, BorderLayout.CENTER);
    }
}
