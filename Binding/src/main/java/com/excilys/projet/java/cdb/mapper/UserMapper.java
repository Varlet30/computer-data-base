package com.excilys.projet.java.cdb.mapper;

import com.excilys.projet.java.cdb.dto.UserDTO;
import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

	public static Users fromUserDTOtoUser(UserDTO userDTO) {

		String passwordHash = new BCryptPasswordEncoder().encode(userDTO.getPassword());
		Users user = new Users(userDTO.getUsername(), passwordHash, true);
		UserRole userRole = new UserRole(user, userDTO.getRole());
		Set<UserRole> userRoleSet = new HashSet<>();

		userRoleSet.add(userRole);
		user.setUserRole(userRoleSet);

		return user;
	}
	
	private UserMapper() {
	    throw new IllegalStateException("Utility class");
	}
}