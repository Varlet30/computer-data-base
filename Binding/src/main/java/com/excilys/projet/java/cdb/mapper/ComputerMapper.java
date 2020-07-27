package com.excilys.projet.java.cdb.mapper;

import java.time.LocalDate;


import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerMapper{
	
	public static Computer convertComputerDTOtoComputer(ComputerDTO compDTO)
	{
		Long id = compDTO.getId();
		String name = compDTO.getName();
		String intro = compDTO.getIntroduced();
		String disco = compDTO.getDiscontinued();
		CompanyDTO compaDTO = compDTO.getCompanyDTO();
		LocalDate introduced = LocalDate.parse(intro);
		LocalDate discontinued = LocalDate.parse(disco);
		
		Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build(); 
		comp.setId(id);
		
		return comp;
	}
	
	public static ComputerDTO convertComputertoComputerDTO(Computer comp)
	{
		Long id = comp.getId();
		String name = comp.getName();
		
		String introduced = null;
		
		if(comp.getIntroduced() != null) {
			introduced = comp.getIntroduced().toString();
		} 
		
		String discontinued = null;
		
		if(comp.getDiscontinued() != null) {
			discontinued = comp.getDiscontinued().toString();
		} 	
		
		Company compa = comp.getCompany();
		CompanyDTO compaDTO = CompanyMapper.convertCompanytoCompanyDTO(compa);
		ComputerDTO compDTO = new ComputerDTO(name, introduced, discontinued, compaDTO); 
		compDTO.setId(id);
		
		return compDTO;
	}
	
	private ComputerMapper() {
	    throw new IllegalStateException("Utility class");
	}
}
