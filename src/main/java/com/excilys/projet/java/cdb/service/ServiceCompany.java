package com.excilys.projet.java.cdb.service;

import java.sql.SQLException;

import java.util.List;

import com.excilys.projet.java.cdb.persistence.dao.CompanyDAO;
import com.excilys.projet.java.cdb.model.Company;

public class ServiceCompany 
{
	private static ServiceCompany instance;
	
	private ServiceCompany()
	{
		
	}
	
	public static ServiceCompany getInstance()
	{
		if (instance == null)
		{
			instance= new ServiceCompany();
			return instance; 
		}
		else
		{
			return instance;
		}
	}
	
	public List<Company> getCompanyList()
	{
		List<Company> listCompa = CompanyDAO.getInstance().allCompany();
		return listCompa;	
	}
	
	public Company getCompany(Long id)
	{
		Company compa = CompanyDAO.getInstance().findCompany(id);
		return compa;
	}
	
	public void getDeleteCompany(Long id) throws SQLException
	{
		CompanyDAO.getInstance().delete(id);
	}
}
