package main.java.com.excilys.projet.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//import main.java.com.excilys.formation.java.cdb.dtos.ComputerDTO;
import main.java.com.excilys.projet.java.cdb.models.Company;
import main.java.com.excilys.projet.java.cdb.models.Computer;

public class ComputerMapper {

	public Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder(resultSet.getString("computer.name"))
				.id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getTimestamp("computer.introduced"))
				.discontinued(resultSet.getTimestamp("computer.discontinued"))
				.company(new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name")).build())
				.build();
	}
}