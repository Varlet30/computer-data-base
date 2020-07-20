package com.excilys.projet.java.cdb.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyDTOTest {
	@Test
	public void testCompanyDto() {
		
		//GIVEN
		
		//WHEN
		CompanyDTO company = new CompanyDTO();
		
		//THEN
		assertEquals(company.getId(), 0L);
		assertEquals(company.getName(), null);
	}
	
	@Test
	public void testCompanyDtoId() {
		
		//GIVEN
		String id = "1";
		//WHEN
		CompanyDTO company = new CompanyDTO(id);
		//THEN
		assertEquals(company.getId(), 1L);
		assertEquals(company.getName(), null);
	}
	
	@Test
	public void testCompanyDtoIdName() {
		
		//GIVEN
		Long id = 1L;
		String name = "company";
		
		//WHEN
		CompanyDTO company = new CompanyDTO(id, name);
		
		//THEN
		assertEquals(company.getId(), 1L);
		assertEquals(company.getName(), "company");
	}
	
	@Test
	public void testCompanyDtoSetId() {
		
		//GIVEN
		Long idSet = 2L;
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		company.setId(idSet);
		
		//THEN
		assertEquals(company.getId(), 2L);
		assertEquals(company.getName(), null);
	}
	
	@Test
	public void testCompanyDtoSetName() {
		
		//GIVEN
		String nameSet = "company";
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		company.setName(nameSet);
		
		//THEN
		assertEquals(company.getId(), 0L);
		assertEquals(company.getName(), "company");
	}
	
	@Test
	public void testCompanyDtoToString() {
		
		//GIVEN
		CompanyDTO company = new CompanyDTO();
		String results;
		
		//WHEN
		results = company.toString();
		
		//THEN
		assertEquals(results, "CompanyDTO [id=0, name=null]");
	}
}
