package com.excilys.projet.java.cdb.persistence.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.mapper.CompanyMapper;
import com.excilys.projet.java.cdb.model.Company;

@Repository
public class CompanyDAO 
{
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	private static final String All = "SELECT * FROM company";
	private static final String FindById = "SELECT * FROM company WHERE id = ?";
	private static final String FindByName = "SELECT * FROM company WHERE name = ?";
	private static final String DeleteByIdComputer = "DELETE FROM computer WHERE company_id = ?";
	private static final String DeleteByIdCompany = "DELETE FROM company WHERE id = ?";
	
	@Autowired
	private ConnecHikari connectHikari;
	
	private CompanyDAO()
	{
	}
	
	public List<Company> allCompany()
	{
		ArrayList<Company> listCompany = new ArrayList<Company>();
		try
		{
			Connection preparation = connectHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(All) ;
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Company compa = CompanyMapper.convertRequest(resultat);
				listCompany.add(compa);
			}
			connectHikari.disconnect();
			return listCompany;
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public Company findCompany(Long id)
	{
		try
		{
			Connection preparation = connectHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(FindById) ;
			prepare.setLong(1, id);
			ResultSet resultat = prepare.executeQuery();
			Company compa = null;
			if(resultat.next())
			{
				compa = CompanyMapper.convertRequest(resultat);
			}
			connectHikari.disconnect();
			return compa;
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public Company findCompany(String name)

	{
		
		try
		{
			Connection preparation = connectHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(FindByName) ;
			prepare.setString(1, name);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Company compa = CompanyMapper.convertRequest(resultat);
				connectHikari.disconnect();
				return compa;
			}
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public int delete(long companyId) throws SQLException
	{
		int value = 0;
		Connection preparation = connectHikari.getConnection();
		preparation.setAutoCommit(false);
		try 
		{
			PreparedStatement prepare = preparation.prepareStatement(DeleteByIdComputer) ;
			prepare.setLong(1, companyId);
			value = prepare.executeUpdate();
			if (value > 0)
			{
				prepare = preparation.prepareStatement(DeleteByIdCompany) ;
				prepare.setLong(1, companyId);
				value = prepare.executeUpdate();
				if (value == 1)
				{
					preparation.commit();
				}
				else
				{
					preparation.rollback();
				}
			}
			else
			{
				preparation.rollback();
			}
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
			preparation.rollback();
		}
		preparation.setAutoCommit(true);
		connectHikari.disconnect();
		return value;
	}
}