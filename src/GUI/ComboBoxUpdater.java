package GUI;

import UserData.Account;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ComboBoxUpdater {
    private static List<JComboBox> accountBoxes = new ArrayList<>();

    //Upon being initialized in their repective GUI element, selectAccount comboboxes are stored in a list.
    public static void addAccountBox(JComboBox comboBox) {
        accountBoxes.add(comboBox);
    }

    //A function that is called after every CRUD-type action on an Account object, to update every combobox during runtime
    public static void updateAccountBoxes(List<Account> accounts) {
        for (JComboBox comboBox:accountBoxes){
            comboBox.removeAllItems();

            for(Account a:accounts){
                comboBox.addItem(new ComboModel(a.getName(), a));
            }
        }
    }
}
