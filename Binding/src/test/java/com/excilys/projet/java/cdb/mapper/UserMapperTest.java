package com.excilys.projet.java.cdb.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.excilys.projet.java.cdb.dto.UserDTO;
import com.excilys.projet.java.cdb.model.Users;

public class UserMapperTest {

	@Test
	public void testComputerDtoToComputer() {
		
		//GIVEN
		
		String username = "test";
		String password = "test";
		String role = "test";
		
		UserDTO userDTO = new UserDTO(username, password, role);
		
		//WHEN
		Users user = UserMapper.fromUserDTOtoUser(userDTO);
		
        Users expUser = new Users();
        expUser.setUsername(username);
        
        //THEN
        assertEquals(expUser.getUsername(), user.getUsername());
	}
}