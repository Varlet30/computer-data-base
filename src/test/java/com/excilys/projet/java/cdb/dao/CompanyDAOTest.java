package com.excilys.projet.java.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.service.ServiceCompany;

@Component
public class CompanyDAOTest {

	@Autowired
	private ServiceCompany serviceCompany;
	
    @Test
    public void testGetAll() throws ClassNotFoundException {
    	List<Company> company = serviceCompany.getCompanyList();
        assertFalse(company.isEmpty());
        assertEquals(42, company.size());
    }

    @Test
    public void testFindCompany() throws ClassNotFoundException {
    	Company company = serviceCompany.getCompany(1L);
        assertEquals("Apple Inc.", company.getName());
    }
}