package com.excilys.projet.java.cdb.mapper;

import java.time.LocalDate;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.dto.ComputerDTO;
import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerMapper 
{
	public static Computer convertComputerDTOtoComputer(ComputerDTO compDTO)
	{
		Long id = compDTO.getIdComputer();
		String name = compDTO.getName();
		String intro = compDTO.getIntroduced();
		String disco = compDTO.getDiscontinued();
		CompanyDTO compaDTO = compDTO.getCompa();
		LocalDate introduced = LocalDate.parse(intro);
		LocalDate discontinued = LocalDate.parse(disco);
		Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build(); 
		comp.setId(id);
		return comp;
	}
}
