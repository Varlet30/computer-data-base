package com.excilys.projet.java.cdb.controleur;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.excilys.projet.java.cdb.spring.TestConfig;
import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AddControllerTest {
	
	@Autowired
	public AddController addController;
	
	@Test
	public void testPostAddController() {
		
		//GIVEN
		String request = "redirect:dashboard?lengthPage=10&column=&tri=0&page=1";
		ModelMap map = new ModelMap();
		String name = "computer";
		String introduced = "1997-03-11";
		String discontinued = "1998-04-12";
		CompanyDTO companyDTO = new CompanyDTO();
		ComputerDTO computerDTO = new ComputerDTO(name, introduced, discontinued, companyDTO);
		
		//WHEN		
		String result =  addController.postAddComputer(computerDTO, 1, map);
		
		//THEN
		assertEquals(result, request);
	}
	
	@Test
	public void testGetAddController() {
		
		//GIVEN
		ModelMap map = new ModelMap();
		String request = "addComputer";
		//WHEN		
		String result =  addController.getAddComputer(map);
		
		//THEN
		assertEquals(result, request);
	}
}
