package com.excilys.projet.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public static CompanyDTO convertInverse(Company compa)
	{
		long id = compa.getId();
		CompanyDTO compaDTO = new CompanyDTO();
		compaDTO.setId(id);
		return compaDTO;
	}
	public static Company convertRequest(ResultSet resultat) throws SQLException
	{
		String name = resultat.getString("name");
		long id = resultat.getLong("id");
		Company compa = new Company.CompanyBuilder().setId(id).setName(name).build();
		return compa;
	}
}
