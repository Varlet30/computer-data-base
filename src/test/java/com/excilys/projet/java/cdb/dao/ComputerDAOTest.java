package com.excilys.projet.java.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@Component
public class ComputerDAOTest {

	@Autowired
	private ServiceComputer serviceComputer;
	
	@Test
    public void testGetAll() throws ClassNotFoundException {
    	List<Computer> Computer = serviceComputer.getComputerList();
        assertFalse(Computer.isEmpty());
        assertEquals(573, Computer.size());
    }

    @Test
    public void testFindComputer() throws ClassNotFoundException {
    	Computer Computer = serviceComputer.findComputerById(100L);
        assertEquals("Z4", Computer.getName());
    }

    @Test
    public void testFirstPage() throws ClassNotFoundException {
        List<Computer> computers = serviceComputer.getComputerListPaginer(0, "computer_name", 10, 1);
        assertFalse(computers.isEmpty());
        assertEquals(10, computers.size());
        assertEquals(3, computers.get(0).getId());
    }
}