package main.java.com.excilys.projet.java.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public final class Connect {

    private static Connection connection;

    private Connect() {
    	System.out.println("Connection...");
        try {
        	Properties properties = new Properties();
            InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
			properties.load(in);
			in.close();
        	
            String url = properties.getProperty("jdbc.url");
            String driver = properties.getProperty("jdbc.driver");
            String userName = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");    
        	
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connecté");
        } catch (SQLException | ClassNotFoundException | IOException e) {
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
	
	

