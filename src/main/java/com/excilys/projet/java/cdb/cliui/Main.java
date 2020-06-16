package main.java.com.excilys.projet.java.cdb.cliui;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        InterfaceClient ic = new InterfaceClient();
        ic.start();
    }
}
