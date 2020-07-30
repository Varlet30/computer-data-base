package com.excilys.projet.cdb.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.TestConfig;
import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;
import com.excilys.projet.java.cdb.service.ServiceUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class ServiceUserTest {

	@Autowired
	private ServiceUser serviceUser;
	
	@Test
	public void testServiceUserLoadUserByUsername() {
		
		//GIVEN
		String username = "admin";
		
		//WHEN
		UserDetails userDetails = serviceUser.loadUserByUsername(username);
		
		//THEN
		assertEquals(username, userDetails.getUsername());
	}
	
	@Test
	public void testServiceUserAddUser() {
		
		//GIVEN
		int results;
		String username = "DAOUser2";
		String password = "DAOPass2";
		String role = "test";
				
		Users users =  new Users();
		users.setUsername(username);
		users.setPassword(password);
		UserRole userRole = new UserRole(users, role);
				
		Set<UserRole> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);
				
		users.setUserRole(userRoleSet);
		
		serviceUser.addUser(users);
		serviceUser.deleteUser(users);
		results = 1;
		
		//THEN
		assertEquals(1, results);
	}	
}
