package GUI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        super(new GridLayout(4, 1));
        createComponents();
    }

    private void createComponents() {
        add(new JButton("Execute"));
        add(new JButton("Test"));
        add(new JButton("Send"));
    }
}