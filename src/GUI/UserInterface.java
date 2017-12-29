package GUI;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {
    private JFrame frame;

    private JPanel navbar;
    private JPanel footer;

    @Override
    public void run() {
        frame = new JFrame(" Stix & Chill");

        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(createMenuPanel(), BorderLayout.WEST);
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
}
