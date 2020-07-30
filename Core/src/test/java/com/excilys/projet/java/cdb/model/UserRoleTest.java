package com.excilys.projet.java.cdb.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class UserRoleTest {
	
	@Test
	public void testCompanyVoid() {
		
		//GIVEN
		
		//WHEN
		UserRole userRole = new UserRole();
		
		//THEN
		assertEquals(null, userRole.getRole());
		assertEquals(null, userRole.getUser());
	}
	
	@Test
	public void testCompany() {
		
		//GIVEN
		String role = "test";
		String username = "test";
		Users user = new Users();
		user.setUsername(username);
		
		//WHEN
		UserRole userRole = new UserRole(user, role);
		
		//THEN
		assertEquals(role, userRole.getRole());
		assertEquals(user, userRole.getUser());
	}
	
	@Test
	public void testCompanySetUser() {
		
		//GIVEN
		String username = "test";
		Users user = new Users();
		user.setUsername(username);
		
		//WHEN
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		
		//THEN
		assertEquals(user, userRole.getUser());
	}
}
