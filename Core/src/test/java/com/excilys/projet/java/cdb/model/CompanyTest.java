package com.excilys.projet.java.cdb.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompanyTest {
	
	@Test
	public void testCompany() {
		
		//GIVEN
		Long Null = 0L;
		
		//WHEN
		Company company = new Company();
		
		//THEN
		assertEquals(Null, company.getId());
		assertEquals(null, company.getName());
	}
	
	@Test
	public void testCompanySetId() {
		
		//GIVEN
		Long id = 1L;
		
		//WHEN
		Company company = new Company();
		company.setId(id);
		
		//THEN
		assertEquals(id, company.getId());
	}
	
	@Test
	public void testCompanySetName() {
		
		//GIVEN
		String name = "company";
		
		//WHEN
		Company company = new Company();
		company.setName(name);
		
		//THEN
		assertEquals(name, company.getName());
	}
	
	@Test
	public void testCompanyIdName() {
		
		//GIVEN
		Long id = 1L;
		String name = "company";
		
		//WHEN
		Company company = new Company.CompanyBuilder().setId(id).setName(name).build();
		
		//THEN
		assertEquals(id, company.getId());
		assertEquals("company", company.getName());
	}
	
	@Test
	public void testCompanyToString() {
		
		//GIVEN
		Company company = new Company();
		String results;
		
		//WHEN
		results = company.toString();
		
		//THEN
		System.out.println(results);
		assertEquals("Company [id=0, name=null]", results);
	}
}