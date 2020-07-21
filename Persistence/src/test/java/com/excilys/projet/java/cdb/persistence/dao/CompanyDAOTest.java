package com.excilys.projet.java.cdb.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.TestConfig;
import com.excilys.projet.java.cdb.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class CompanyDAOTest {

	@Autowired
	private CompanyDAO companyDao;
	
	@Test
	public void testAllCompanyNb() {
		
		//GIVEN
		int NbCompany = 42;
		
		//WHEN
		List<Company> listCompany = companyDao.allCompany();   
		
		//THEN
		assertEquals(listCompany.size(), NbCompany);
	}
	
	@Test
	public void testFindCompanyId() {
		
		//GIVEN
		String NameCompany = "Apple Inc.";
		
		//WHEN
		Company Company = companyDao.findCompany(1L);   
		
		//THEN
		assertEquals(Company.getName(), NameCompany);
	}
	
	@Test
	public void testFindCompanyName() {
		
		//GIVEN
		String NameCompany = "Apple Inc.";
		
		//WHEN
		Company Company = companyDao.findCompany("Apple Inc.");   
		
		//THEN
		assertEquals(Company.getName(), NameCompany);
	}
}