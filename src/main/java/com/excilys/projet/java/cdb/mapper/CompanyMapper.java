package com.excilys.projet.java.cdb.mapper;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.model.Company;

public class CompanyMapper 
{
	public static Company convert(CompanyDTO compaDTO)
	{
		long id = compaDTO.getId();
		Company compa = new Company.CompanyBuilder().setId(id).build();
		return compa;
	}
}
