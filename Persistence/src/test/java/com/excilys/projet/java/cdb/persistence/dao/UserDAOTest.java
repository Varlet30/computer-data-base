package com.excilys.projet.java.cdb.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.TestConfig;
import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testUserDAOGetUser() {
		
		//GIVEN
		String username = "admin";
		
		//WHEN 
		List<Users> user = userDAO.getUser(username);
		Users result = user.get(0);
		//THEN
		assertEquals(username, result.getUsername());
	}
	
	@Test
	public void testUserDAOCreateUser() {
		
		//GIVEN
		String username = "DAOUsernames";
		String password = "DAOPasswords";
		String role = "test";
		
		Users users =  new Users();
		users.setUsername(username);
		users.setPassword(password);
		UserRole userRole = new UserRole(users, role);
		
		Set<UserRole> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);
		
		users.setUserRole(userRoleSet);
		
		//WHEN 
		userDAO.createUser(users);
		
		List<Users> user = userDAO.getUser(username);
		Users result = user.get(0);
		
		//THEN
		assertEquals(username, result.getUsername());
	}
	
	@Test
	public void testUserDAODeleteUser() {
		
		//GIVEN
		String username = "DAOUsernames";
		
		//WHEN 
		List<Users> users = userDAO.getUser(username);
		Users user = users.get(0);
		userDAO.deleteUser(user);
		users = userDAO.getUser(username);
		
		//THEN
		assertEquals(new ArrayList<>(), users);
	}
	
	@Test
	public void testUserDAOGetUserRole() {
		
		//GIVEN
		String username = "admin";
		String role = "ADMIN";
		
		//WHEN 
		List<UserRole> user = userDAO.getUserRole(username);
		UserRole result = user.get(0);
		//THEN
		assertEquals(role, result.getRole());
	}
}