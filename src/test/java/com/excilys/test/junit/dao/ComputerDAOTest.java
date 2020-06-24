package com.excilys.test.junit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Test;

import com.excilys.projet.java.cdb.models.Company;
import com.excilys.projet.java.cdb.models.Computer;
import com.excilys.projet.java.cdb.models.Page;
import com.excilys.projet.java.cdb.persistence.DAOs.ComputerDAO;

public class ComputerDAOTest {

    private ComputerDAO computerDAO;

    @BeforeAll
    public void setUp() throws InvocationTargetException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Constructor<ComputerDAO> computerDAOConstructor = ComputerDAO.class.getDeclaredConstructor();
        assertEquals(computerDAOConstructor.isAccessible(), false);
        computerDAOConstructor.setAccessible(true);
        computerDAO = computerDAOConstructor.newInstance();
        }
    @AfterAll
   	protected static void tearDownAfterClass() throws Exception {
   	}

   	@BeforeEach
   	protected void setup() throws Exception {
   	}

   	@AfterEach
   	protected void tearDown() throws Exception {
   	}

    @Test
    public void testGetNumberComputers() {
        int nbComputers = computerDAO.getNumberComputers();
        assertEquals(50, nbComputers);
    }

    @Test
    public void testGetAllByPageFirstPage() {
        List<Computer> computers = computerDAO.getAllByPage(new Page(8, 1));
        assertFalse(computers.isEmpty());
        assertEquals(8, computers.size());
        assertEquals(1L, computers.get(0).getIdComputer().longValue());
    }

    @Test
    public void testGetAllByPageSecondPage() {
        List<Computer> computers = computerDAO.getAllByPage(new Page(8, 2));
        assertFalse(computers.isEmpty());
        assertEquals(8, computers.size());
        assertEquals(9L, computers.get(0).getIdComputer().longValue());
    }

    @Test
    public void testGetAllByPageNegative() {
        List<Computer> computers = computerDAO.getAllByPage(new Page(8, -3));
        assertTrue(computers.isEmpty());
    }

    @Test
    public void testGetAllByPageZero() {
        List<Computer> computers = computerDAO.getAllByPage(new Page(8, 0));
        assertTrue(computers.isEmpty());
    }

    @Test
    public void testGetAllByPageExceeded() {
        List<Computer> computers = computerDAO.getAllByPage(new Page(8, 8));
        assertTrue(computers.isEmpty());
    }

    @Test
    public void testFindExistingId() {
        Optional<Computer> optComputer = computerDAO.findById(2L);
        assertTrue(optComputer.isPresent());
        assertEquals(2L, optComputer.get().getIdComputer(), 0);
        assertEquals("CM-2a", optComputer.get().getName());
    }

    @Test
    public void testFindIdNotExisting() {
        Optional<Computer> optComputer = computerDAO.findById(55L);
        assertFalse(optComputer.isPresent());
    }

    @Test
    public void testFindIdNull() {
        Optional<Computer> optComputer = computerDAO.findById(null);
        assertFalse(optComputer.isPresent());
    }
    
    @Test
    public void testFindNegativeId() {
        Optional<Computer> optComputer = computerDAO.findById(-1L);
        assertFalse(optComputer.isPresent());
    }
    
    @Test
    public void testFindIdZero() {
        Optional<Computer> optComputer = computerDAO.findById(0L);
        assertFalse(optComputer.isPresent());
    }
    
    @Test
    public void testCreate() {
        Computer computer = new Computer();
        computer.setName("computer Test");
        computer.setIntroducedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        computer.setDiscontinuedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        computer.setCompany(new Company(1L));
        assertEquals(50, computerDAO.getNumberComputers());
        computerDAO.create(computer);
        assertEquals(51, computerDAO.getNumberComputers());
    }


    @Test
    public void testUpdate() {
        Computer computer = new Computer();
        computer.setIdComputer(1L);
        computer.setName("computer Test 1");
        computer.setIntroducedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        computer.setDiscontinuedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        computer.setCompany(new Company(1L));
        computerDAO.update(computer);
        assertEquals("computer Test 1", computerDAO.findById(1L).get().getName());
    }

    @Test
    public void testDelete() {
        assertTrue(computerDAO.findById(1L).isPresent());
        computerDAO.delete(1L);
        assertFalse(computerDAO.findById(1L).isPresent());
    }
}