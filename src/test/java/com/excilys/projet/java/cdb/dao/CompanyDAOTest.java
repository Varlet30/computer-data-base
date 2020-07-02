package com.excilys.projet.java.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.service.ServiceCompany;

public class CompanyDAOTest {

    @Test
    public void testGetAll() throws ClassNotFoundException {
    	ArrayList<Company> company = ServiceCompany.getInstance().getCompanyList();
        assertFalse(company.isEmpty());
        assertEquals(42, company.size());
    }

    @Test
    public void testFindCompany() throws ClassNotFoundException {
    	Company company = ServiceCompany.getInstance().getCompany(1L);
        assertEquals("Apple Inc.", company.getName());
    }
}