package com.excilys.projet.java.cdb.mapper;

import java.sql.*;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.projet.java.cdb.dto.CompanyDTO;
import com.excilys.projet.java.cdb.model.Company;

public class CompanyMapper implements RowMapper<Company>
{
	public Company mapRow(ResultSet resultat, int i) throws SQLException 
	{
		Company compa = new Company.CompanyBuilder().build();
		compa.setId(resultat.getLong("id"));
		compa.setName(resultat.getString("name"));
		return compa;
	}
	
	public static Company convertCompanyDTOtoCompany(CompanyDTO compaDTO)
	{
		long id=compaDTO.getId();
		Company compa = new Company.CompanyBuilder().setId(id).build();
		return compa;
	}
	public static CompanyDTO convertCompanytoCompanyDTO(Company compa)
	{
		long id=compa.getId();
		String name=compa.getName();
		CompanyDTO compaDTO = new CompanyDTO(id,name);
		return compaDTO;
	}
}