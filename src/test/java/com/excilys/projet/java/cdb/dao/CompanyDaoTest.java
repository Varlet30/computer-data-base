package com.excilys.projet.java.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.persistence.dao.CompanyDAO;

@RunWith(SpringRunner.class)
public class CompanyDaoTest {

	@TestConfiguration
    static class CompanyDaoTestContextConfiguration {
 
        @Bean
        public CompanyDAO companyDAO() {
            return new CompanyDAO(null, null);
        }
    }
	
	@Autowired
	private CompanyDAO companyDAO;

	@Test
	public void testGetAll() {
		System.out.println("test");
	    List<Company> companie = companyDAO.allCompany();
	    System.out.println(companie);
	    assertFalse(companie.isEmpty());
	}
}