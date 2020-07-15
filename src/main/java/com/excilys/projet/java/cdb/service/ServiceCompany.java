package com.excilys.projet.java.cdb.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.persistence.dao.CompanyDAO;

@Service
public class ServiceCompany 
{
	private CompanyDAO companyDao;
	
	private ServiceCompany(CompanyDAO companyDao)
	{
		this.companyDao = companyDao;
	}
	
	public List<Company> getCompanyList()
	{
		List<Company> listCompa = companyDao.allCompany();
		
		return listCompa;	
	}
	
	public Company getCompany(Long id)
	{
		Company compa = companyDao.findCompany(id);
		
		return compa;
	}
	
	public void getDeleteCompany(Long id) throws SQLException
	{
		companyDao.deleteCompany(id);
	}
}
