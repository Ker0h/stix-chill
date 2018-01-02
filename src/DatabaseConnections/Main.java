package DatabaseConnections;

import UserData.Account;

public class Main {

    public static void main(String[] args) {
        SQLExecutor sqlExecutor = new SQLExecutor();
        sqlExecutor.getAccounts();
        System.out.println("--------------------------------------------------------------------");
        sqlExecutor.getSeries();
        System.out.println("--------------------------------------------------------------------");
        Account acc = new Account(1215426, "Fam. van Raalte", "Schopenhauerdijkje", "5","3991 ML" , "Houten");
        sqlExecutor.getProfiles(acc);
    }
}