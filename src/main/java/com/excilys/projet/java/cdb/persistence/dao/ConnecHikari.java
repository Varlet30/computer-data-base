package com.excilys.projet.java.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnecHikari 
{

	private static Connection connect;
	private static ConnecHikari instance;
	private static HikariConfig hikariConfig; 
	private static HikariDataSource dataSource;
	private static Logger logger = LoggerFactory.getLogger(ConnecHikari.class);
	
	static 
	{
  	hikariConfig = new HikariConfig("/datasource.properties");
  	dataSource = new HikariDataSource(hikariConfig);
  	}

	public ConnecHikari() { 
	}

	public Connection getConnection() {
		try {
			connect = dataSource.getConnection();
		} catch (SQLException sqlException) {
			logger.error("error connection", sqlException);
		}
		return connect;
	}

	public static synchronized ConnecHikari getInstance() {
		if (instance == null) {
			instance = new ConnecHikari();
		}
		return instance;
	}

	public synchronized Connection disconnect() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException sqlException) {
				logger.error("error connection", sqlException);
			}
		}
		return connect;
	}
}