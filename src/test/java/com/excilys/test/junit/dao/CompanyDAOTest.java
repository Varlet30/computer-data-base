package com.excilys.test.junit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Test;

import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.models.Page;
import com.excilys.projet.java.cdb.persistence.DAOs.CompanyDAO;
import com.excilys.projet.java.cdb.persistence.DAOs.ComputerDAO;

public class CompanyDAOTest {

    private CompanyDAO companyDAO;
    private ComputerDAO computerDAO;

    @BeforeAll
    public void setup() throws NoSuchMethodException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Constructor<CompanyDAO> companyDAOConstructor = CompanyDAO.class.getDeclaredConstructor();
        assertEquals(companyDAOConstructor.isAccessible(), false);
        companyDAOConstructor.setAccessible(true);
        companyDAO = companyDAOConstructor.newInstance();
        Constructor<ComputerDAO> computerDAOConstructor = ComputerDAO.class.getDeclaredConstructor();
        assertEquals(computerDAOConstructor.isAccessible(), false);
        computerDAOConstructor.setAccessible(true);
        computerDAO = computerDAOConstructor.newInstance();
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
    public void testGetAll() {
        List<Company> companies = companyDAO.getAll();
        assertFalse(companies.isEmpty());
        assertEquals(10, companies.size());
    }

    @Test
    public void testGetAllByPageFirstPage() {
        List<Company> companies = companyDAO.getAllByPage(new Page(8, 1));
        assertFalse(companies.isEmpty());
        assertEquals(8, companies.size());
        assertEquals(1L, companies.get(0).getIdCompany().longValue());
    }

    @Test
    public void testGetAllByPageSecondPage() {
        List<Company> companies = companyDAO.getAllByPage(new Page(8, 2));
        assertFalse(companies.isEmpty());
        assertEquals(2, companies.size());
        assertEquals(9L, companies.get(0).getIdCompany().longValue());
    }

    @Test
    public void testGetAllByPageNegative() {
        List<Company> companies = companyDAO.getAllByPage(new Page(8, -3));
        assertTrue(companies.isEmpty());
    }

    @Test
    public void testGetAllByPageZero() {
        List<Company> companies = companyDAO.getAllByPage(new Page(8, 0));
        assertTrue(companies.isEmpty());
    }

    @Test
    public void testGetAllByPageExceeded() {
        List<Company> companies = companyDAO.getAllByPage(new Page(8, 3));
        assertTrue(companies.isEmpty());
    }

    @Test
    public void testFindExistingId() {
        Optional<Company> optCompany = companyDAO.findById(2L);
        assertTrue(optCompany.isPresent());
        assertEquals(2L, optCompany.get().getIdCompany(), 0);
        assertEquals("Thinking Machines", optCompany.get().getName());
    }

    @Test
    public void testFindIdNotExisting() {
        Optional<Company> optCompany = companyDAO.findById(15L);
        assertFalse(optCompany.isPresent());
    }

    @Test
    public void testFindNegativeId() {
        Optional<Company> optCompany = companyDAO.findById(-1L);
        assertFalse(optCompany.isPresent());
    }

    @Test
    public void testDelete() {
        assertTrue(companyDAO.findById(1L).isPresent());
        Page page = new Page(60, 1);
        assertEquals(50, computerDAO.getAllByPage(page).size());
        companyDAO.delete(1L);
        assertFalse(companyDAO.findById(1L).isPresent());
        assertEquals(32, computerDAO.getAllByPage(page).size());
    }
}