package com.excilys.projet.java.cdb.cliui;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
        InterfaceClient ic = new InterfaceClient();
        ic.start();
    }
}
