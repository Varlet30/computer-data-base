package com.excilys.projet.java.cdb.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerMapperTest {

	@Test
	public void testComputerDtoToComputer() {
		
		//GIVEN
		String name = "computer";
		String introduced = "1997-03-11";
		String discontinued = "1998-04-12";
		CompanyDTO companyDTO = new CompanyDTO();
		ComputerDTO computerDTO = new ComputerDTO(name, introduced, discontinued, companyDTO);
		
		//WHEN
		Computer computer = ComputerMapper.convertComputerDTOtoComputer(computerDTO);
        Computer expComputer = new Computer();
        expComputer.setName(name);
        expComputer.setIntroduced(LocalDate.parse(introduced));
        expComputer.setDiscontinued(LocalDate.parse(discontinued));
        expComputer.setCompany(new Company());
        
        //THEN
        assertEquals(expComputer.getName(), computer.getName());
        assertEquals(expComputer.getIntroduced(), computer.getIntroduced());
        assertEquals(expComputer.getDiscontinued(), computer.getDiscontinued());
	}
	
	@Test
	public void testComputerToComputerDto() {
		
		//GIVEN
		String name = "computer";
		LocalDate introducedLocalDate = LocalDate.parse("1997-03-11");
		LocalDate discontinuedLocalDate = LocalDate.parse("1998-04-12");
		String introduced = "1997-03-11";
		String discontinued = "1998-04-12";
		Company company = new Company();
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(introducedLocalDate);
		computer.setDiscontinued(discontinuedLocalDate);
		computer.setCompany(company);
		
		//WHEN
		ComputerDTO computerDTO = ComputerMapper.convertComputertoComputerDTO(computer);
		ComputerDTO expComputer = new ComputerDTO();
		expComputer.setName(name);
		expComputer.setIntroduced(introduced);
		expComputer.setDiscontinued(discontinued);
		expComputer.setCompanyDTO(new CompanyDTO());
		
        //THEN
		assertEquals(expComputer.getName(), computerDTO.getName());
        assertEquals(expComputer.getIntroduced(), computerDTO.getIntroduced());
        assertEquals(expComputer.getDiscontinued(), computerDTO.getDiscontinued());
	}	
}
