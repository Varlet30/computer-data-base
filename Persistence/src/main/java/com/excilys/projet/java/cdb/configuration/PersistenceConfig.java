package com.excilys.projet.java.cdb.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.excilys.projet.java.cdb.model", "com.excilys.projet.java.cdb.persistence.dao", "com.excilys.projet.java.cdb.mapper" })
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class PersistenceConfig {

	@Autowired
	Environment environment;

	private final String DRIVER = "driverClassName";
	private final String URL = "jdbcUrl";
	private final String USER = "username2";
	private final String PASSWORD = "password";

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
		driverManagerDataSource.setUrl(environment.getRequiredProperty(URL));
		driverManagerDataSource.setUsername(environment.getRequiredProperty(USER));
		driverManagerDataSource.setPassword(environment.getRequiredProperty(PASSWORD));

		return driverManagerDataSource;
	}
	
	@Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
	
	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
	    return entityManagerFactory.createEntityManager();
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.excilys.projet.java.cdb.model");
		return sessionFactory;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
