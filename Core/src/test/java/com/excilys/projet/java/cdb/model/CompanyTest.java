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
		assertEquals(company.getId(), Null);
		assertEquals(company.getName(), null);
	}
	
	@Test
	public void testCompanySetId() {
		
		//GIVEN
		Long id = 1L;
		
		//WHEN
		Company company = new Company();
		company.setId(id);
		
		//THEN
		assertEquals(company.getId(), id);
	}
	
	@Test
	public void testCompanySetName() {
		
		//GIVEN
		String name = "company";
		
		//WHEN
		Company company = new Company();
		company.setName(name);
		
		//THEN
		assertEquals(company.getName(), name);
	}
	
	@Test
	public void testCompanyIdName() {
		
		//GIVEN
		Long id = 1L;
		String name = "company";
		
		//WHEN
		Company company = new Company.CompanyBuilder().setId(id).setName(name).build();
		
		//THEN
		assertEquals(company.getId(), id);
		assertEquals(company.getName(), "company");
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
		assertEquals(results, "Company [id=0, name=null]");
	}
}