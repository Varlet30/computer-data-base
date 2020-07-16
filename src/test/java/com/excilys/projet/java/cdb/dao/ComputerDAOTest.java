package com.excilys.projet.java.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

public class ComputerDAOTest {

	@Test
    public void testGetAll() throws ClassNotFoundException {
    	List<Computer> Computer = ServiceComputer.getInstance().getComputerList();
        assertFalse(Computer.isEmpty());
        assertEquals(573, Computer.size());
    }

    @Test
    public void testFindComputer() throws ClassNotFoundException {
    	Computer Computer = ServiceComputer.getInstance().findComputerById(100L);
        assertEquals("Z4", Computer.getName());
    }

    @Test
    public void testFirstPage() throws ClassNotFoundException {
        List<Computer> computers = ServiceComputer.getInstance().getComputerListPaginer(0, "computer_name", 10, 1);
        assertFalse(computers.isEmpty());
        assertEquals(10, computers.size());
        assertEquals(3, computers.get(0).getId());
    }
}