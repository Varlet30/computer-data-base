package com.excilys.projet.java.cdb.persistence.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Computer;

@Repository
public class ComputerDAO
{
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static String CreateComputer = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name,:introduced,:discontinued,:idCompany)";
	public static String FindComputerId = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=:id";
	public static String FindComputerName = "SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE :research OR LOWER(company.name) LIKE :research OR introduced LIKE :research OR discontinued LIKE :research;";
	public static String UpdateComputer = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :idCompany WHERE id = :idComputer";
	public static String DeleteComputer = "DELETE FROM computer WHERE id = :id";
	public static String DeleteComputerByCompa = "DELETE FROM computer WHERE company_id = :id";
	public static String Select = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id";
	public static String Limit = " LIMIT :offset, :limit";
	public static String Ascendant= " ASC";
	public static String Descendant = " DESC";
	public static String Order = " ORDER BY ";

	public ComputerDAO(DataSource dataSource) 
	{
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Computer> allComputer() 
	{
		return namedParameterJdbcTemplate.query(Select,new ComputerMapper());
	}
	
	public int countComputer() 
	{
		List<Computer> ListComputer = allComputer();
		
		return ListComputer.size();
	}
	
	public List<Computer> pageComputer(int tri, String column, int limit, int offset) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("limit",limit)
				.addValue("offset",offset);
		String requete;
		
		if (tri == 0||column == ""||column == null) 
		{
			requete = Select + Limit;
			
		} 
		else if (tri == 1) 
		{
			requete = Select + Order + column + Ascendant + Limit;
				
		} 
		else 
		{
			requete = Select + Order + column + Descendant + Limit;
		}
		
		return namedParameterJdbcTemplate.query(requete, namedParameters, new ComputerMapper());
	}
	
	public Computer findComputerId (long id) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		
		return namedParameterJdbcTemplate.queryForObject(FindComputerId, namedParameters, new ComputerMapper());
	}
	
	public List<Computer> findComputerName (String research) 
	{
		research = research.toLowerCase();
		research = "%"+ research +"%";
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("research",research);
		
		return namedParameterJdbcTemplate.query(FindComputerName, namedParameters, new ComputerMapper());
	}
	
	public int updateComputer(Computer comp) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name", comp.getName())
				.addValue("introduced", comp.getIntroduced())
				.addValue("name", comp.getName())
				.addValue("discontinued", comp.getDiscontinued())
				.addValue("idComputer", comp.getId())
				.addValue("idCompany", comp.getCompany().getId());
		
		return namedParameterJdbcTemplate.update(UpdateComputer, namedParameters);
	}
	
	public int deleteComputer(long id) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id", id);
		
		return namedParameterJdbcTemplate.update(DeleteComputer, namedParameters);
	}
	
	public int deleteComputerByCompany(long id) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id", id);
		
		return namedParameterJdbcTemplate.update(DeleteComputerByCompa, namedParameters);
	}
	
	public int createComputer(Computer comp) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name", comp.getName())
				.addValue("introduced", comp.getIntroduced())
				.addValue("name", comp.getName())
				.addValue("discontinued", comp.getDiscontinued())
				.addValue("idCompany", comp.getCompany().getId());
		
		return namedParameterJdbcTemplate.update(CreateComputer, namedParameters);
	}
}