package com.excilys.projet.java.cdb.mapper;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.model.Company;

public class CompanyMapper{
	
	public static Company convertCompanyDTOtoCompany(CompanyDTO compaDTO)
	{
		long id = compaDTO.getId();
		
		return new Company.CompanyBuilder().setId(id).build();
	}
	public static CompanyDTO convertCompanytoCompanyDTO(Company compa)
	{
		CompanyDTO compaDTO = null;
		
		if (compa != null)
		{
			long id = compa.getId();
			String name = compa.getName();
			compaDTO = new CompanyDTO(id, name);
		}
		return compaDTO;
	}
	
	private CompanyMapper() {
	    throw new IllegalStateException("Utility class");
	}
}