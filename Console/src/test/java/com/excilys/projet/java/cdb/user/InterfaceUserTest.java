package com.excilys.projet.java.cdb.user;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

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
public class InterfaceUserTest {
	
	@Autowired
	public InterfaceUser interfaceUser;

	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceAddComputer() throws ParseException{

		//GIVEN
		int results;
		
		String choice = "1";
		String name = "test";
		String introduced = null;
		String discontinued = null;
		String company = null;
		
		String scanner = choice+"\n"+name+"\n"+introduced+"\n"+discontinued+"\n"+company;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceAddComputerDateParser() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "1";
		String name = "test";
		String introduced = "1997-03-11";
		String discontinued = "1998-04-12";
		String companyId = "5";
		
		String scanner = choice+"\n"+name+"\n"+introduced+"\n"+discontinued+"\n"+companyId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserUpdateComputerById() throws ParseException, SQLException {
		
		//GIVEN
		int results;
		String choice = "2";
		String name = "computer";
		String introduced = null;
		String discontinued = null;
		String companyId = null;
		
		String scanner = choice+"\n"+name+"\n"+introduced+"\n"+discontinued+"\n"+companyId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
				
		//THEN
		assertEquals(0, results);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserUpdateComputerByIdDateParser() throws ParseException, SQLException {
		
		//GIVEN
		int results;
		String choice = "2";
		String name = null;
		String introduced = "1997-03-11";
		String discontinued = "1998-04-12";
		String companyId = "5";
		
		String scanner = choice+"\n"+name+"\n"+introduced+"\n"+discontinued+"\n"+companyId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
				
		//THEN
		assertEquals(0, results);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserDeleteCompanyById() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "3";
		String computerId = "5";
		
		String scanner = choice+"\n"+computerId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserComputerByName() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "4";
		String computerName = "test";
		
		String scanner = choice+"\n"+computerName;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserDisplayComputer() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "5";
		
		String scanner = choice;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserDisplayCompany() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "6";
		
		String scanner = choice;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceUserFindCompanyById() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "7";
		String companyId = "5";
		
		String scanner = choice+"\n"+companyId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceDisplayPageComputer() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "8";
		String limit = "10";
		String offset = "10";
		
		String scanner = choice+"\n"+limit+"\n"+offset;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceDeleteComputer() throws ParseException, SQLException {
		
		//GIVEN
		int results = 0;
		
		String choice = "9";
		String computerId = "5";
		
		String scanner = choice+"\n"+computerId;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(0, results);  
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInterfaceExit() throws ParseException, SQLException {
		
		//GIVEN
		int results;
		String choice = "10";
		
		String scanner = choice;
		ByteArrayInputStream in = new ByteArrayInputStream(scanner.getBytes());
		System.setIn(in);
		
		//WHEN
		try {
			interfaceUser.displayInterface();  
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
				
		//THEN
		assertEquals(1, results);
		
	}
}