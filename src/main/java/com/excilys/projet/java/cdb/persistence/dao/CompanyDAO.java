package com.excilys.projet.java.cdb.persistence.dao;

import java.sql.*;

import java.util.ArrayList;

import com.excilys.projet.java.cdb.model.Company;

public class CompanyDAO 
{
	private static CompanyDAO instance;
	
	private CompanyDAO()
	{
	}
	
	public static CompanyDAO getInstance()
	{
		if (instance == null)
		{
			instance = new CompanyDAO();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	private static Connection connexionOpen() throws ClassNotFoundException
	{
		Connection preparation = ConnecHikari.getInstance().getConnection();
		return preparation;
	}
	
	private static void connexionClose(Connection preparation) throws ClassNotFoundException
	{
		ConnecHikari.getInstance().disconnect();
	}
	
	public ArrayList<Company> allCompany() throws ClassNotFoundException
	{
		ArrayList<Company> listCompany = new ArrayList<Company>();
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT * FROM company") ;
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				String name = resultat.getString("name");
				long id = resultat.getLong("id");
				Company compa = new Company.CompanyBuilder().setId(id).setName(name).build();
				listCompany.add(compa);
			}
			CompanyDAO.connexionClose(preparation);
			return listCompany;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Company findCompany(Long id) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT * FROM company WHERE id = ?") ;
			prepare.setLong(1, id);
			ResultSet resultat = prepare.executeQuery();
			Company compa = null;
			if(resultat.next())
			{
				long idCompany = resultat.getLong("id");
				String name = resultat.getString("name");
				compa = new Company.CompanyBuilder().setId(idCompany).setName(name).build();
			}
			CompanyDAO.connexionClose(preparation);
			return compa;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Company findCompany(String name) throws ClassNotFoundException

	{
		
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT * FROM company WHERE name = ?") ;
			prepare.setString(1, name);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				long id = resultat.getLong("id");
				String nameCompany = resultat.getString("name");

				Company compa = new Company.CompanyBuilder().setId(id).setName(nameCompany).build();

				CompanyDAO.connexionClose(preparation);
				return compa;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public int delete(long companyId) throws ClassNotFoundException, SQLException
	{
		int value = 0;
		Connection preparation = CompanyDAO.connexionOpen();
		preparation.setAutoCommit(false);
		try 
		{
			PreparedStatement prepare = preparation.prepareStatement("DELETE FROM computer WHERE company_id = ?") ;
			prepare.setLong(1, companyId);
			value = prepare.executeUpdate();
			if (value > 0)
			{
				prepare = preparation.prepareStatement("DELETE FROM company WHERE id = ?") ;
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
			e.printStackTrace();
			preparation.rollback();
		}
		preparation.setAutoCommit(true);
		CompanyDAO.connexionClose(preparation);
		return value;
	}
}