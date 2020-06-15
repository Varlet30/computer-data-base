package main.java.com.excilys.projet.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//import main.java.com.excilys.formation.java.cdb.dtos.ComputerDTO;
import main.java.com.excilys.projet.java.cdb.models.Company;

public class CompanyMapper {
	
	public Company getCompany(ResultSet resultSet) throws SQLException {
		return new Company.CompanyBuilder(resultSet.getLong("id"),resultSet.getString("name"))
			.build();
		
	}

}
