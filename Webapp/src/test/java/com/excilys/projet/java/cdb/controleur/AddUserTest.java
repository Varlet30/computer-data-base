package com.excilys.projet.java.cdb.controleur;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.projet.java.cdb.dto.UserDTO;
import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;
import com.excilys.projet.java.cdb.spring.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AddUserTest {
	
	@Autowired
	public AddUser addUser;
	
	@Test
	public void testAddUserRegister() {
		
		//GIVEN
		
		//WHEN		
		ModelAndView result = addUser.register();
		
		//THEN
		assertEquals("addUser", result.getViewName());
	}
	
	@Test
	public void testAddUserSettMessage() {
		
		//GIVEN
		String errorMessage = null; 
		String messageTitle = null;
		ModelAndView modelAndView = null;
		
		//WHEN		
		addUser.setMessage(errorMessage, messageTitle, modelAndView);
		
		//THEN
		assertEquals(null, modelAndView);
	}
}
