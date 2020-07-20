package com.excilys.projet.cdb.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.PersistenceConfig;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.service.ServiceCompany;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
public class ServiceCompanyTest {

	@Autowired
	private ServiceCompany serviceCompany;
	
	@Test
	public void testAllCompanyNb() {
		
		//GIVEN
		int NbCompany = 42;
		
		//WHEN
		List<Company> listCompany = serviceCompany.getCompanyList();   
		
		//THEN
		assertEquals(listCompany.size(), NbCompany);
	}
	
	@Test
	public void testFindCompanyId() {
		
		//GIVEN
		String NameCompany = "Apple Inc.";
		
		//WHEN
		Company Company = serviceCompany.getCompany(1L);   
		
		//THEN
		assertEquals(Company.getName(), NameCompany);
	}
}