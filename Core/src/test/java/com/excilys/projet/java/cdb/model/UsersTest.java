package com.excilys.projet.java.cdb.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class UsersTest {
	
	@Test
	public void testCompanyVoid() {
		
		//GIVEN
		
		//WHEN
		Users user = new Users();
		
		//THEN
		assertEquals(null, user.getUsername());
		assertEquals(null, user.getPassword());
		assertEquals(false, user.isEnabled());
	}
	
	@Test
	public void testCompanyWithEnabled() {
		
		//GIVEN

		Boolean enabled = true;
		String username = "test";
		String password = "test";
		
		//WHEN
		Users user = new Users(username, password, enabled);
		
		//THEN
		assertEquals("test", user.getUsername());
		assertEquals("test", user.getPassword());
		assertEquals(true, user.isEnabled());
	}
	
	@Test
	public void testCompanyWithUserRole() {
		
		//GIVEN
		String userRole = "test";
		String username = "test";
		String password = "test";

		Users userTest = new Users();	
		
		UserRole usersRole = new UserRole(userTest, userRole);
		
		//WHEN
		Users user = new Users(username, password, usersRole);
		Set<UserRole> resultRole = new HashSet<>();
		resultRole.add(usersRole);
		
		//THEN
		assertEquals("test", user.getUsername());
		assertEquals("test", user.getPassword());
		assertEquals(resultRole, user.getUserRole());
	}
	
	@Test
	public void testCompanySetUsername() {
		
		//GIVEN
		String username = "test";
		
		//WHEN
		Users user = new Users();
		user.setUsername(username);
		
		//THEN
		assertEquals(username, user.getUsername());
	}
	
	@Test
	public void testCompanySetPassword() {
		
		//GIVEN
		String password = "test";
		
		//WHEN
		Users user = new Users();
		user.setPassword(password);
		
		//THEN
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testCompanyIsEnabled() {
		
		//GIVEN
		Boolean enabled = true;
		
		//WHEN
		Users user = new Users();
		user.setEnabled(enabled);
		
		//THEN
		assertEquals(enabled, user.isEnabled());
	}
	
	@Test
	public void testCompanySetUserRole() {
		
		//GIVEN
		String username = "test";
		String userRole = "test";

		Users user = new Users();
		user.setUsername(username);		
		
		UserRole usersRole = new UserRole(user, userRole);
		
		//WHEN
		Set<UserRole> role = new HashSet<>();
		role.add(usersRole);
		user.setUserRole(role);
		
		//THEN
		assertEquals(role, user.getUserRole());
	}
}