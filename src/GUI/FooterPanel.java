package GUI;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    FooterPanel(){
        super(new FlowLayout());

        this.add(new JLabel("Informatica"));
        this.add(new JLabel("2017"));
        this.add(new JLabel("Klas F"));
        this.add(new JLabel("Stijn van Veen, 2125141"));
        this.add(new JLabel("Yannick Willems, 2128086"));
        this.add(new JLabel("Jop van Wijnen, <studentnummer>"));
    }
}
