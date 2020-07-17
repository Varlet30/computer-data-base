package com.excilys.projet.java.cdb.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.Environment;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = {"com.excilys.projet.java.cdb.persistence.dao", 
							"com.excilys.projet.java.cdb.controlleur",
							"com.excilys.projet.java.cdb.service",
							"com.excilys.projet.java.cdb.controleur",
							"com.excilys.projet.java.cdb.mapper"})
@PropertySource("classpath:datasource.properties")
public class SpringConfig extends AbstractContextLoaderInitializer 
{
	 @Override
	 protected WebApplicationContext createRootApplicationContext() 
	 {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		 rootContext.register(SpringConfig.class);
		 
		 return rootContext;
	 }

	@Autowired
	Environment environment;

	@Bean
	DataSource getConnection() 
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		
    	driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/computer-database-db?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
		driverManagerDataSource.setUsername("admincdb");
		driverManagerDataSource.setPassword("qwerty1234");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return driverManagerDataSource;
	}
	
	@Bean
	NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) 
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		return namedParameterJdbcTemplate;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getConnection());
		sessionFactory.setPackagesToScan("com.excilys.projet.java.cdb.model");
		return sessionFactory;
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
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class,SpringMVCConfig.class);
		webContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(webContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}