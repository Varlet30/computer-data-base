package com.excilys.projet.java.cdb.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerDTOTest {	
	@Test
	public void testCompanyDto() {
		
		//GIVEN
		
		//WHEN
		ComputerDTO computer = new ComputerDTO();
		
		//THEN
		assertEquals(computer.getName(), null);
		assertEquals(computer.getIntroduced(), null);
		assertEquals(computer.getDiscontinued(), null);
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
		assertEquals(computer.getName(), "computer");
		assertEquals(computer.getIntroduced(), "1997-03-11");
		assertEquals(computer.getDiscontinued(), "1997-03-11");
		assertEquals(computer.getCompanyDTO().getId(), 0L);
		assertEquals(computer.getCompanyDTO().getName(), null);
	}
	
	@Test
	public void testCompanyDtoSetName() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerName = "computer";
		
		//WHEN
		computer.setName(computerName);
		
		//THEN
		assertEquals(computer.getName(), "computer");
	}
	
	@Test
	public void testCompanyDtoSetIntroduced() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerIntroduced = "1997-03-11";
		
		//WHEN
		computer.setIntroduced(computerIntroduced);
		
		//THEN
		assertEquals(computer.getIntroduced(), "1997-03-11");
	}
	
	@Test
	public void testCompanyDtoSetDiscontinued() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		String computerDiscontinued = "1997-03-11";
		
		//WHEN
		computer.setDiscontinued(computerDiscontinued);
		
		//THEN
		assertEquals(computer.getDiscontinued(), "1997-03-11");
	}
	
	@Test
	public void testCompanyDtoSetCompany() {
		
		//GIVEN
		ComputerDTO computer = new ComputerDTO();
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		computer.setCompanyDTO(company);
		
		//THEN
		assertEquals(computer.getCompanyDTO().getId(), 0L);
		assertEquals(computer.getCompanyDTO().getName(), null);
	}
	
}
