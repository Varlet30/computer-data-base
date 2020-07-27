package com.excilys.projet.java.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.persistence.dao.CompanyDAO;

@Service
public class ServiceCompany 
{
	@Autowired
	private CompanyDAO companyDao;
	
	public List<Company> getCompanyList()
	{
		return companyDao.allCompany();
	}
	
	public Company getCompany(Long id)
	{
		return companyDao.findCompany(id);
	}
	
	public void getDeleteCompany(Long id)
	{
		companyDao.deleteCompany(id);
	}
}
