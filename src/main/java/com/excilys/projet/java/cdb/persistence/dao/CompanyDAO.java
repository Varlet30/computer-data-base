package com.excilys.projet.java.cdb.persistence.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.mapper.CompanyMapper;
import com.excilys.projet.java.cdb.model.Company;

@Repository
public class CompanyDAO 
{
	private ComputerDAO computerDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public static String FindCompanyId = "SELECT * FROM company WHERE id = :id";
	public static String FindCompanyName = "SELECT * FROM company WHERE name = :name";
	public static String AllCompany = "SELECT * FROM company";
	public static String DeleteCompany = "DELETE FROM company WHERE id = :id";
	
	public CompanyDAO(DataSource dataSource,ComputerDAO computerDao) {
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
		this.computerDao=computerDao;
	}
	
	public List<Company> allCompany() {
		return namedParameterJdbcTemplate.query(AllCompany,new CompanyMapper());
	}
	
	public Company findCompany (long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.queryForObject(FindCompanyId, namedParameters, new CompanyMapper());
	}
	
	public Company findCompany(String name) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name",name);
		return namedParameterJdbcTemplate.queryForObject(FindCompanyName, namedParameters, new CompanyMapper());
	}
	
	public int deleteCompany(long companyId) {
		computerDao.deleteComputerByCompany(companyId);
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",companyId);
		return namedParameterJdbcTemplate.update(DeleteCompany,namedParameters);
	}
}