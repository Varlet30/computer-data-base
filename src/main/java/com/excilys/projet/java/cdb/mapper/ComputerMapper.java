package main.java.com.excilys.projet.java.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.excilys.projet.java.cdb.models.Company;
import main.java.com.excilys.projet.java.cdb.models.Computer;

public class ComputerMapper {

	public static Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder(resultSet.getString("computer.name"))
				.id(resultSet.getLong("computer.id"))
				.introduced(resultSet.getDate("computer.introduced"))
				.discontinued(resultSet.getDate("computer.discontinued"))
				.company(new Company.CompanyBuilder(resultSet.getLong("computer.id"), resultSet.getString("computer.name")).build())
				.build();
	}
}