package main.java.com.excilys.projet.java.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connect {

    private static Connection connection;

    private Connect() {
    	System.out.println("Connection...");
        try {
            String url = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
        	String driver = "com.mysql.cj.jdbc.Driver";
        	String userName = "admincdb";
        	String password = "qwerty1234";      
        	
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connecté");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getInstance() {
        if (connection == null) {
            new Connect();
        }
        return connection;
    }

}
	
	

