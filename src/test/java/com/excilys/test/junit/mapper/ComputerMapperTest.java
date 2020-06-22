package com.excilys.test.junit.mapper;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.models.Computer;

public class ComputerMapperTest {

    private static final String IDCOMPUTER = "computer.id";
    private static final String NAMECOMPUTER = "computer.name";
    private static final String INTRODUCEDCOMPUTER = "computer.introduced";
    private static final String DISCONTINUEDCOMPUTER = "computer.discontinued";
    private static final String IDCOMPANY = "id";
    private static final String NAMECOMPANY = "name";

    private final Long idComputer = 10L;
    private final String computerName = "computer name";
    private final Date introduced = new Date(100L);
    private final Date discontinued = new Date(200L);
    private final Long idCompany = 11L;
    private final String companyName = "company name";

    private ResultSet resultSet = mock(ResultSet.class);

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
    public void testConvert() throws SQLException {
        try {
            when(resultSet.getLong(IDCOMPUTER)).thenReturn(idComputer);
            when(resultSet.getString(NAMECOMPUTER)).thenReturn(computerName);
            when(resultSet.getDate(INTRODUCEDCOMPUTER)).thenReturn(introduced);
            when(resultSet.getDate(DISCONTINUEDCOMPUTER)).thenReturn(discontinued);
            when(resultSet.getLong(IDCOMPANY)).thenReturn(idCompany);
            when(resultSet.getString(NAMECOMPANY)).thenReturn(companyName);
        } catch (SQLException e) {
            fail("sql exception :" + e.getMessage());
        }
        Computer computer = ComputerMapper.getComputer(resultSet);
        Computer expComputer = new Computer();
        
        expComputer.setIdComputer(idComputer);
        expComputer.setName(computerName);
        java.sql.Date sqlIntroduced = new java.sql.Date(introduced.getTime());
        expComputer.setIntroducedDate(sqlIntroduced);
        java.sql.Date sqlDiscontinued = new java.sql.Date(discontinued.getTime());
        expComputer.setDiscontinuedDate(sqlDiscontinued);
        expComputer.setCompany(new Company(idCompany, companyName));
        
        assertEquals(expComputer, computer);
    }
}    
    