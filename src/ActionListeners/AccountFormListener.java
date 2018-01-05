package ActionListeners;

import DatabaseConnections.SQLExecutor;
import GUI.AccountForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFormListener implements ActionListener {
    private SQLExecutor exe;

    public AccountFormListener(SQLExecutor exe){
        this.exe = exe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new AccountForm());
    }
}
