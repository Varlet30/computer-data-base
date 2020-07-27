package com.excilys.projet.java.cdb.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

public class ComputerTest {	
	
	@Test
	public void testCompany() {
		
		//GIVEN
		
		//WHEN
		Computer computer = new Computer();
		
		//THEN
		assertEquals(null, computer.getName());
		assertEquals(null, computer.getIntroduced());
		assertEquals(null, computer.getDiscontinued());
	}
	
	@Test
	public void testComputerSetName() {
		
		//GIVEN
		String name = "computer";
		
		//WHEN
		Computer computer = new Computer();
		computer.setName(name);
		
		//THEN
		assertEquals(name, computer.getName());
	}
	
	@Test
	public void testComputerSetId() {
		
		//GIVEN
		Long id = 1L;
		
		//WHEN
		Computer computer = new Computer();
		computer.setId(id);
		Long result = computer.getId();
		
		//THEN
		assertEquals(id, result);
	}
	
	@Test
	public void testComputerComplet() {
		
		//GIVEN
		Long Null = 0L;
		String name = "computer";
		LocalDate introduced = LocalDate.parse("1997-03-11");
		LocalDate discontinued = LocalDate.parse("1998-04-12");
		Company company = new Company();
		
		//WHEN
		Computer computer = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		
		//THEN
		assertEquals("computer", computer.getName());
		assertEquals(LocalDate.parse("1997-03-11"), computer.getIntroduced());
		assertEquals(LocalDate.parse("1998-04-12"), computer.getDiscontinued());
		assertEquals(Null, computer.getCompany().getId());
		assertEquals(null, computer.getCompany().getName());
	}	

	@Test 
	public void testComputerToString() {
		
		//GIVEN
		Computer computer = new Computer();
		String results;
				
		//WHEN
		results = computer.toString();
		//THEN
		assertEquals("Computer [id=0, name=null, introduced=null, discontinued=null, compa=null]", results);
	}
	
	@Test 
	public void testComputerSetElement() {
		
		//GIVEN
		Computer computer = new Computer();
		Company company = new Company();
		String name = "computer";
		LocalDate introduced = LocalDate.parse("1997-03-11");
		LocalDate discontinued = LocalDate.parse("1998-04-12");
				
		//WHEN
		computer.setName(name);
		computer.setCompany(company);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
	
		//THEN
		assertEquals("Computer [id=0, name=computer, introduced=1997-03-11, discontinued=1998-04-12, compa=Company [id=0, name=null]]", computer.toString());
	}
	
	@Test 
	public void testComputerSetDatePrevious() {
		
		//GIVEN
		Computer computer = new Computer();
		LocalDate introduced = LocalDate.parse("1997-03-11");
		LocalDate discontinued = LocalDate.parse("1996-03-11");
				
		//WHEN
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		
		//THEN
		assertEquals("Computer [id=0, name=null, introduced=1997-03-11, discontinued=null, compa=null]", computer.toString());
	}
	
	@Test 
	public void testComputerSetDateNull() {
		
		//GIVEN
		Computer computer = new Computer();
		LocalDate introduced = null;
		LocalDate discontinued = LocalDate.parse("1997-03-11");
				
		//WHEN
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		
		//THEN
		assertEquals("Computer [id=0, name=null, introduced=null, discontinued=1997-03-11, compa=null]", computer.toString());
	}
	
	@Test 
	public void testComputerSetDate() {
		
		//GIVEN
		Computer computer = new Computer();
		LocalDate introduced = LocalDate.parse("1997-03-11");
		LocalDate discontinued = LocalDate.parse("1998-03-11");
				
		//WHEN
		computer.setDiscontinued(discontinued);
		computer.setIntroduced(introduced);
		
		//THEN
		assertEquals("Computer [id=0, name=null, introduced=1997-03-11, discontinued=1998-03-11, compa=null]", computer.toString());
	}
}