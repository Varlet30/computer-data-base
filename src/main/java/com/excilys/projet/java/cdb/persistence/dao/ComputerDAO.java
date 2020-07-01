package com.excilys.projet.java.cdb.persistence.dao;

import java.sql.*;

import java.util.ArrayList;

import java.time.LocalDate;

import com.excilys.projet.java.cdb.model.Company;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerDAO
{
	
	private ComputerDAO()
	{
	}
	
	private static ComputerDAO instance; 
	
	public static ComputerDAO getInstance()
	{
		if (instance == null)
		{
			instance= new ComputerDAO();
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
	
	public ArrayList<Computer> allComputer() throws ClassNotFoundException
	{
		
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id") ;
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				String name = resultat.getString("computer.name");
				Date intro = resultat.getDate("computer.introduced");
				Date disco = resultat.getDate("computer.discontinued");
				LocalDate introduced = null;
				if (intro != null)
				{
					introduced = intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco != null)
				{
					discontinued = disco.toLocalDate();
				}
				String companyName = resultat.getString("company.name");
				
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id = resultat.getLong("computer.id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);
				if (comp.getName() != null)
				{
					listComputer.add(comp);
				}
			}
			ComputerDAO.connexionClose(preparation);
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public int count() throws ClassNotFoundException
	{	
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		int nombreComputer = 0;
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
					+ " FROM computer LEFT JOIN company ON computer.company_id = company.id");
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				String name = resultat.getString("computer.name");
				Date intro = resultat.getDate("computer.introduced");
				Date disco = resultat.getDate("computer.discontinued");
				LocalDate introduced = null;
				if (intro != null)
				{
					introduced = intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco != null)
				{
					discontinued = disco.toLocalDate();
				}
				Long company_id = resultat.getLong("company.id");
				
				Company compa = CompanyDAO.getInstance().findCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			

				if (comp.getName() != null)
				{
					listComputer.add(comp);
				}
				nombreComputer = listComputer.size();
			}
			ComputerDAO.connexionClose(preparation);
			return nombreComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Computer> pageComputer(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		String requete;
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			if (tri==0||colonne==null)
			{
				requete = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id" + " LIMIT ?, ?";
			}
			else
			{
				if (tri==1)
				{
					requete = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id" + " ORDER BY " + colonne + " ASC" + " LIMIT ?, ?";
				}
				else
				{
					requete = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id" + " ORDER BY " + colonne + " DESC" + " LIMIT ?, ?";
				}
			}
			PreparedStatement prepare = preparation.prepareStatement(requete);
			prepare.setInt(2, limit);
			prepare.setInt(1, offset);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				String name = resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro != null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco != null)
				{
					discontinued = disco.toLocalDate();
				}
				String companyName = resultat.getString("company_name");
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id = resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);

				if (comp.getName()!= null)
				{
					listComputer.add(comp);
				}
			}
			ComputerDAO.connexionClose(preparation);
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Computer findId (long id) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT * FROM computer WHERE id = ?") ;
			prepare.setLong(1, id);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 

				String name = resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro != null)
				{
					introduced = intro.toLocalDate();
				}
				
				LocalDate discontinued = LocalDate.now();
				if (disco!=null)
				{
					discontinued = disco.toLocalDate();
				}
				long company_id = resultat.getLong("company_id");
				
				Company compa = CompanyDAO.getInstance().findCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			

				if (comp.getName() != null)
				{
					return comp;
				}
				
				else
				{
					return null;
				}
			}
			ComputerDAO.connexionClose(preparation);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Computer> findName (String recherche) throws ClassNotFoundException
	{
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		try
		{
			Connection preparation = ComputerDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement("SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ? OR introduced LIKE ? OR discontinued LIKE ?;") ;
			recherche = recherche.toLowerCase();
			recherche = "%"+recherche+"%";
			prepare.setString(1,recherche);
			prepare.setString(2,recherche);
			prepare.setString(3,recherche);
			prepare.setString(4,recherche);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				String name = resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro != null)
				{
					introduced = intro.toLocalDate();
				}
				LocalDate discontinued = LocalDate.now();
				if (disco != null)
				{
					discontinued = disco.toLocalDate();
				}
				String companyName = resultat.getString("company_name");
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id = resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);
				listComputer.add(comp);
			}
			ComputerDAO.connexionClose(preparation);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listComputer;
	}
	
	public int update(Computer comp) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = ComputerDAO.connexionOpen();
		if (comp.getName() != null)
		{
			try
			{
				PreparedStatement prepare = preparation.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
				prepare.setString(1, comp.getName());
				if (comp.getIntroduced() != null)
				{
					prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
				}
				if (comp.getDiscontinued() != null)
				{
					prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
				}
				if (comp != null)
				{
					if (comp.getCompany() != null)
					{
						if (comp.getCompany().getId() != null)
						{
							prepare.setLong(4, comp.getCompany().getId());
						}
						else
						{
							prepare.setNull(4, java.sql.Types.BIGINT);
						}
					}
				}
				prepare.setLong(5,comp.getId());
				value = prepare.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
	
	public int delete(long computerId) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = ComputerDAO.connexionOpen();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement("DELETE FROM computer WHERE id = ?") ;
			prepare.setLong(1, computerId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
	public int deleteComputIdCompany(long companyId) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = ComputerDAO.connexionOpen();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement("DELETE FROM computer WHERE company_id = ?") ;
			prepare.setLong(1, companyId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
	public int create(Computer comp) throws ClassNotFoundException
	{
		Connection preparation = ComputerDAO.connexionOpen();
		int value = 0;
		try
		{
			PreparedStatement prepare = preparation.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)") ;
			prepare.setString(1, comp.getName());
			if (comp.getIntroduced() != null)
			{
				prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
			}
			if (comp.getDiscontinued() != null)
			{
				prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
			}
			if (comp != null)
			{
				if (comp.getCompany() != null)
				{
					if (comp.getCompany().getId() != null)
					{
						prepare.setLong(4, comp.getCompany().getId());
					}
					else
					{
						prepare.setNull(4, java.sql.Types.BIGINT);
					}
				}
			}
			value = prepare.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.connexionClose(preparation);
		return value;
	}
}