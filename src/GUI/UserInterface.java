package GUI;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {
    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame(" Stix & Chill");

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(createMenuPanel(), BorderLayout.WEST);
        container.add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JPanel createMenuPanel(){
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        panel.add(new JButton("Overzicht 1"));
        panel.add(new JButton("Overzicht 2"));
        panel.add(new JButton("Overzicht 3"));
        panel.add(new JButton("Overzicht 4"));

        return panel;
    }

    private JPanel createFooterPanel(){
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        panel.setLayout(layout);

        panel.add(new JLabel("Informatica"));
        panel.add(new JLabel("2017"));
        panel.add(new JLabel("Klas F"));
        panel.add(new JLabel("Stijn van Veen, <studentnummer>"));
        panel.add(new JLabel("Yannick Willems, 2128086"));
        panel.add(new JLabel("Jop van Wijnen, <studentnummer>"));

        return panel;
    }
}
