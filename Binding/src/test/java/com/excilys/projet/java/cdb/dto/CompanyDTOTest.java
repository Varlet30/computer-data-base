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
		assertEquals(0L, company.getId());
		assertEquals(null, company.getName());
	}
	
	@Test
	public void testCompanyDtoId() {
		
		//GIVEN
		String id = "1";
		
		//WHEN
		CompanyDTO company = new CompanyDTO(id);
		
		//THEN
		assertEquals(1L, company.getId());
		assertEquals(null, company.getName());
	}
	
	@Test
	public void testCompanyDtoIdName() {
		
		//GIVEN
		Long id = 1L;
		String name = "company";
		
		//WHEN
		CompanyDTO company = new CompanyDTO(id, name);
		
		//THEN
		assertEquals(1L, company.getId());
		assertEquals("company", company.getName());
	}
	
	@Test
	public void testCompanyDtoSetId() {
		
		//GIVEN
		Long idSet = 2L;
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		company.setId(idSet);
		
		//THEN
		assertEquals(2L, company.getId());
		assertEquals(null, company.getName());
	}
	
	@Test
	public void testCompanyDtoSetName() {
		
		//GIVEN
		String nameSet = "company";
		CompanyDTO company = new CompanyDTO();
		
		//WHEN
		company.setName(nameSet);
		
		//THEN
		assertEquals(0L, company.getId());
		assertEquals("company", company.getName());
	}
	
	@Test
	public void testCompanyDtoToString() {
		
		//GIVEN
		CompanyDTO company = new CompanyDTO();
		String results;
		
		//WHEN
		results = company.toString();
		
		//THEN
		assertEquals("CompanyDTO [id=0, name=null]", results);
	}
}
