package main.java.com.excilys.projet.java.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Connect {

    private static Connection connection;

    private Logger logger = LoggerFactory.getLogger(Connect.class);

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
            logger.error("error connecting", e);
        }
    }

    public static synchronized Connection getInstance() {
        if (connection == null) {
            new Connect();
        }
        return connection;
    }

}
	
	

