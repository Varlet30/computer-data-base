package com.excilys.projet.java.cdb.controleur;

import static org.junit.Assert.assertEquals;

import java.security.Principal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.projet.java.cdb.spring.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class LoginTest {
	
	@Autowired
	public Login login;
	
	@Test
	public void testLoginPage() {
		
		//GIVEN
		
		//WHEN		
		ModelAndView result = login.loginPage();
		
		//THEN
		assertEquals("login", result.getViewName());
	}
	
	@Test
	public void testLoginAccessDenied() {
		
		//GIVEN
		Principal principal = null;
		
		//WHEN		
		ModelAndView result = login.accessDenied(principal);
		
		//THEN
		assertEquals("403", result.getViewName());
	}

}
