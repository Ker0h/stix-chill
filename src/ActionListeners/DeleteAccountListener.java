package ActionListeners;

import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountListener implements ActionListener {
    private SQLExecutor exe;
    private String sub;
    private JPanel panel;

    public DeleteAccountListener(SQLExecutor exe, String sub, JPanel panel) {
        this.exe = exe;
        this.sub = sub;
        this.panel = panel;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exe.deleteAccount(sub);
        panel.revalidate();
    }
}
