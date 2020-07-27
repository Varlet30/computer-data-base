package com.excilys.projet.java.cdb.user;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class MainTest {
	
	@Autowired
	public InterfaceUser interfaceUser;
	
	@Test
	public void testMain() throws ParseException, SQLException {
		
		//GIVEN
		int results;
		String choice = "10";
		String scanner = choice;
		
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		//WHEN
		try {
			Main.main(null); 
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
				
		//THEN
		assertEquals(1, results);
	}
}