package com.excilys.projet.cdb.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.TestConfig;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;
import com.excilys.projet.java.cdb.service.ServiceComputer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class ServiceComputerTest {

	@Autowired
	private ServiceComputer serviceComputer;
	
	@Test
	public void testAllComputerNb() {
		
		//GIVEN
		int nbComputer = 569;
		
		//WHEN
		List<Computer> listComputer = serviceComputer.getComputerList();   
		
		//THEN
		assertEquals(listComputer.size(), nbComputer);
	}
	
	@Test
	public void testAllCount() {
		
		//GIVEN
		int nbComputer = 569;
		
		//WHEN
		int listComputerSize = serviceComputer.getCount();   
		
		//THEN
		assertEquals(listComputerSize, nbComputer);
	}
	
	@Test
	public void testFindComputerId() {
		
		//GIVEN
		String nameComputer = "Z4";
		
		//WHEN
		Computer computer = serviceComputer.findComputerById(100L);   
		
		//THEN
		assertEquals(computer.getName(), nameComputer);
	}
	
	@Test
	public void testPageComputer0() {
		
		//GIVEN
		String nameComputer = "Commodore SX-64";
		
		//WHEN
		List<Computer> computers = serviceComputer.getComputerListPaginer(0, "", 10, 50 );   
		
		//THEN
		assertEquals(computers.get(0).getName(), nameComputer);
	}	
	
	@Test
	public void testFindComputerNameCompany() {
		
		//GIVEN
		Company company = new Company();
		company.setId(1L);
		company.setName("Apple Inc.");
		
		Computer expComputer = new Computer();
		expComputer.setId(5L);
		expComputer.setName("CM-5");
		expComputer.setIntroduced(LocalDate.parse("1990-12-30"));
		expComputer.setDiscontinued(LocalDate.parse("2020-06-30"));
		expComputer.setCompany(company);
		
		//WHEN
		List<Computer> computers = serviceComputer.findComputerByName("Apple Inc.");   
		
		//THEN
		assertEquals(computers.get(0).getName(), expComputer.getName());
		assertEquals(computers.get(0).getIntroduced(), expComputer.getIntroduced());
	}
	
	@Test
	public void testDeleteComputer() {
		
		//GIVEN
		int results;
		Long idComputer  = 5L;
		int win = 1;
		
		//WHEN
		try {
			serviceComputer.deleteComputer(idComputer);   
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		}
		//THEN
		assertEquals(results, win);
	}
	
	@Test
	public void testUpdateComputer() {
		
		//GIVEN
		int results = 0;
		int win = 1;
		
		//WHEN
		Computer computer = new Computer();
		try {
			serviceComputer.updateComputer(computer);    
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		}
		
		//THEN
		assertEquals(results, win);
	}
	
	@Test
	public void testAddComputer() {
		
		//GIVEN
		int results = 0;
		int win = 1;
		String computerName = "Test";
		
		//WHEN
		Computer computer = new Computer();
		computer.setName(computerName);
		try {
			serviceComputer.addComputer(computer);   
			results = 1;
		}catch (NullPointerException e) {
			results = 0;
		} 
		
		//THEN
		assertEquals(results, win);
	}
}