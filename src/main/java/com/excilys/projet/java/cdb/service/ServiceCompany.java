package com.excilys.projet.java.cdb.service;

import java.sql.SQLException;

import java.util.ArrayList;

import com.excilys.projet.java.cdb.persistence.dao.CompanyDAO;
import com.excilys.projet.java.cdb.model.Company;

public class ServiceCompany 
{
	private static ServiceCompany instance;
	
	private ServiceCompany() throws ClassNotFoundException
	{
		
	}
	
	public static ServiceCompany getInstance() throws ClassNotFoundException
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
	
	public ArrayList<Company> getCompanyList() throws ClassNotFoundException
	{
		ArrayList<Company> listCompa = CompanyDAO.getInstance().allCompany();
		return listCompa;	
	}
	
	public Company getCompany(Long id) throws ClassNotFoundException
	{
		Company compa = CompanyDAO.getInstance().findCompany(id);
		return compa;
	}
	
	public void getDeleteCompany(Long id) throws ClassNotFoundException, SQLException
	{
		CompanyDAO.getInstance().delete(id);
	}
}
