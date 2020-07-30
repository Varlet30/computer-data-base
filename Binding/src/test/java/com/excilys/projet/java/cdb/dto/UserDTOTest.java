package com.excilys.projet.java.cdb.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDTOTest {	
	
	@Test
	public void testUserDtoVoid() {
		
		//GIVEN
		String username = null;
		String password = null;
		String role = null;
		
		//WHEN
		UserDTO user = new UserDTO();
		
		//THEN
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole());
	}
	
	@Test
	public void testUserDto() {
		
		//GIVEN
		String username = "test";
		String password = "test";
		String role = "test";
		
		//WHEN
		UserDTO user = new UserDTO(username, password, role);
		
		//THEN
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole());
	}
	
	@Test
	public void testUserDtoSetUsername() {
		
		//GIVEN
		String username = "test";
		
		//WHEN
		UserDTO user = new UserDTO();
		user.setUsername(username);
		
		//THEN
		assertEquals(username, user.getUsername());
	}
	
	@Test
	public void testUserDtoSetPassword() {
		
		//GIVEN
		String password = "test";
		
		//WHEN
		UserDTO user = new UserDTO();
		user.setPassword(password);
		
		//THEN
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testUserDtoSetRole() {
		
		//GIVEN
		String role = "test";
		
		//WHEN
		UserDTO user = new UserDTO();
		user.setRole(role);
		
		//THEN
		assertEquals(role, user.getRole());
	}
}