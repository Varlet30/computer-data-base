package com.excilys.projet.java.cdb.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerDTOTest {	
	
	@Test
	public void testCompanyDto() {
		
		//GIVEN
		String name = null;
		String introduced = null;
		String discontinued = null;
		
		//WHEN
		ComputerDTO computer = new ComputerDTO();
		
		//THEN
		assertEquals(name, computer.getName());
		assertEquals(introduced, computer.getIntroduced());
		assertEquals(discontinued, computer.getDiscontinued());
	}
	
	@Test
	public void testComputerDtoComplet() {
		
		//GIVEN
		String name = "computer";
		String introduced = "1997-03-11";
		String discontinued = "1997-03-11";
		CompanyDTO companyDTO = new CompanyDTO();
		
		//WHEN
		ComputerDTO computer = new ComputerDTO(name, introduced, discontinued, companyDTO);
		
		//THEN
		assertEquals("computer", computer.getName());
		assertEquals("1997-03-11", computer.getIntroduced());
		assertEquals("1997-03-11", computer.getDiscontinued());
		assertEquals(0L, computer.getCompanyDTO().getId());
		assertEquals(null, computer.getCompanyDTO().getName());
	}
	
	@Test
	public void testCompanyDtoSetName() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerName = "computer";
		
		//WHEN
		computer.setName(computerName);
		
		//THEN
		assertEquals("computer", computer.getName());
	}
	
	@Test
	public void testCompanyDtoSetIntroduced() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerIntroduced = "1997-03-11";
		
		//WHEN
		computer.setIntroduced(computerIntroduced);
		
		//THEN
		assertEquals("1997-03-11", computer.getIntroduced());
	}
	
	@Test
	public void testCompanyDtoSetDiscontinued() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerDiscontinued = "1997-03-11";
		
		//WHEN
		computer.setDiscontinued(computerDiscontinued);
		
		//THEN
		assertEquals("1997-03-11", computer.getDiscontinued());
	}
	
	@Test
	public void testCompanyDtoSetCompany() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		computer.setCompanyDTO(company);
		
		//THEN
		assertEquals(0L, computer.getCompanyDTO().getId());
		assertEquals(null, computer.getCompanyDTO().getName());
	}
	
	@Test 
	public void testComputerToString() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String results;
				
		//WHEN
		results = computer.toString();
				
		//THEN
		assertEquals("ComputerDTO [id=0, name=null, introduced=null, discontinued=null, company=null]", results);
	}
}