package com.excilys.projet.java.cdb.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.projet.java.cdb.configuration.PersistenceConfig;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
public class ComputerDAOTest {

	@Autowired
	private ComputerDAO ComputerDAO;
	
	@Test
	public void testAllComputerNb() {
		
		//GIVEN
		int nbComputer = 569;
		
		//WHEN
		List<Computer> listComputer = ComputerDAO.allComputer();   
		
		//THEN
		assertEquals(listComputer.size(), nbComputer);
	}
	
	@Test
	public void testAllCount() {
		
		//GIVEN
		int nbComputer = 569;
		
		//WHEN
		int listComputerSize = ComputerDAO.countComputer();   
		
		//THEN
		assertEquals(listComputerSize, nbComputer);
	}
	
	@Test
	public void testFindComputerId() {
		
		//GIVEN
		String nameComputer = "Z4";
		
		//WHEN
		Computer computer = ComputerDAO.findComputerId(100L);   
		
		//THEN
		assertEquals(computer.getName(), nameComputer);
	}
	
	@Test
	public void testPageComputer0() {
		
		//GIVEN
		String nameComputer = "Commodore SX-64";
		
		//WHEN
		List<Computer> computers = ComputerDAO.pageComputer(0, "", 10, 50 );   
		
		//THEN
		assertEquals(computers.get(0).getName(), nameComputer);
	} 
	
	@Test
	public void testPageComputer1() {
		
		//GIVEN
		String nameComputer = "ASCI Blue Mountain";
		
		//WHEN
		List<Computer> computers = ComputerDAO.pageComputer(1, "name", 10, 50 );   
		
		//THEN
		assertEquals(computers.get(0).getName(), nameComputer);
	} 
	
	@Test
	public void testPageComputer2() {
		
		//GIVEN
		String nameComputer = "Toshiba Satellite";
		
		//WHEN
		List<Computer> computers = ComputerDAO.pageComputer(2, "name", 10, 50 );   
		
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
		List<Computer> computers = ComputerDAO.findComputerName("Apple Inc.");   
		
		//THEN
		assertEquals(computers.get(0).getName(), expComputer.getName());
		assertEquals(computers.get(0).getIntroduced(), expComputer.getIntroduced());
	}
	
	@Test
	public void testDeleteComputer() {
		
		//GIVEN
		int results = 0;
		Long idComputer  = 5L;
		int win = 1;
		
		//WHEN
		results = ComputerDAO.deleteComputer(idComputer);   
		
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
		results = ComputerDAO.updateComputer(computer);   
		
		//THEN
		assertEquals(results, win);
	}
	
	@Test
	public void testCreateComputer() {
		
		//GIVEN
		int results = 0;
		int win = 1;
		String computerName = "Test";
		
		//WHEN
		Computer computer = new Computer();
		computer.setName(computerName);
		results = ComputerDAO.createComputer(computer);   
		
		//THEN
		assertEquals(results, win);
	}
}
