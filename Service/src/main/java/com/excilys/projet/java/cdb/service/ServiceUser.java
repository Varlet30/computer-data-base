package com.excilys.projet.java.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.persistence.dao.UserDAO;
import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;

@Service
public class ServiceUser  implements UserDetailsService {

	private UserDAO userDao;

	public ServiceUser(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username){

		Users user;
		try {
		user = userDao.getUser(username).get(0);

		}
		catch(IndexOutOfBoundsException indexOutOfBoundsException) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		List<UserRole> roles = userDao.getUserRole(username);
		List<GrantedAuthority> grantList = new ArrayList<>();


		if (roles != null) {
			for (String role : getRoleAsString(roles)) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
				grantList.add(authority);
			}
		}

		return new User(user.getUsername(),	user.getPassword(), grantList);
	}

	private List<String> getRoleAsString (List<UserRole> userRoles) {
		List<String> rolesAsString = new ArrayList<>();

		for (UserRole userRole: userRoles) {
			rolesAsString.add(userRole.getRole());
		}

		return rolesAsString;
	}

	public void addUser(Users user) {

		userDao.createUser(user);
	}
	
	public void deleteUser(Users user) {

		userDao.deleteUser(user);
	}
}