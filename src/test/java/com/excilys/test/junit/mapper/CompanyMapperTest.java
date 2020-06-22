package com.excilys.test.junit.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.mapper.CompanyMapper;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyMapperTest {
	
	private static final String IDCOMPANY = "id";
    private static final String NAMECOMPANY = "name";
    private ResultSet resultSet = mock(ResultSet.class);

    private final Long id = 10L;
    private final String name = "name";

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}

	@Test
	public void testGetCompany() throws Exception {
			try {
				when(resultSet.getLong(IDCOMPANY)).thenReturn(id);
				when(resultSet.getString(NAMECOMPANY)).thenReturn(name);
			} catch (SQLException e) {
				fail("sql exception :" + e.getMessage());
			}
			
			Company company = CompanyMapper.getCompany(resultSet);
	        Company expCompany = new Company(id, name);
	        
	        assertEquals(expCompany.getIdCompany(), company.getIdCompany());
	        assertEquals(expCompany.getName(), company.getName());
	}

}
