package com.excilys.projet.java.cdb.persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.projet.java.cdb.mapper.ComputerMapper;
import com.excilys.projet.java.cdb.model.Computer;

public class ComputerDAO
{
	private static ComputerDAO instance; 
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	private static final String AllComputer = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced as computerIntroduced, "
			+ "computer.discontinued as computerDiscontinued, company.id, as companyId company.name as companyName FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id";
	private static final String ListComputer = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced as computerIntroduced, "
			+ "computer.discontinued as computerDiscontinued, company.id as companyId, company.name as companyName FROM computer "
			+ "LEFT JOIN company ON computer.company_id = company.id";
	private static final String PageOrder = "SELECT computer.name as computerName, computer.id as computerId, "
			+ "computer.introduced as computerIntroduced, computer.discontinued as computerDiscontinued, computer.company_id, "
			+ "company.name as companyName FROM computer LEFT JOIN company on "
			+ "company.id=computer.company_id";
	private static final String FindById = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced as computerIntroduced, "
			+ "computer.discontinued as computerDiscontinued, company_id AS companyId FROM computer WHERE id = ?";
	private static final String FindByName = "SELECT  computer.name as computerName, computer.id as computerId, "
			+ "computer.introduced as computerIntroduced, computer.discontinued as computerDiscontinued, computer.company_id as compuanyId, "
			+ "company.name as companyName FROM computer LEFT JOIN company on "
			+ "companyId=computer.company_id WHERE LOWER(computer.name) LIKE ? "
			+ "OR LOWER(company.name) LIKE ? OR introduced LIKE ? OR discontinued LIKE ?;";
	private static final String Update = "UPDATE computer SET omputerName = ?, computerIntroduced = ?, computerDiscontinued = ?, computer.company_id = ? WHERE computerId = ?";
	private static final String DeleteByIdComputer = "DELETE FROM computer WHERE id = ?";
	private static final String DeleteByIdCompany = "DELETE FROM computer WHERE company_id = ?";
	private static final String Insert = "INSERT computer (computerName, computerIntroduced, computerDiscontinued, computer.company_id) VALUES (?,?,?,?)";
	
	
	private ComputerDAO()
	{
	}
	
	public static synchronized ComputerDAO getInstance()
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
	
	public List<Computer> allComputer()
	{
		
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		try
		{
			Connection preparation = ConnecHikari.getInstance().getConnection();
			PreparedStatement prepare = preparation.prepareStatement(AllComputer) ;
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Computer comp = ComputerMapper.convertRequestByName(resultat);
				if (comp.getName() != null)
				{
					listComputer.add(comp);
				}
			}
ConnecHikari.getInstance().disconnect();
			return listComputer;
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public int count()
	{	
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		int nombreComputer = 0;
		try
		{
			Connection preparation = ConnecHikari.getInstance().getConnection();
			PreparedStatement prepare = preparation.prepareStatement(ListComputer);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Computer comp = ComputerMapper.convertRequestById(resultat);
				if (comp.getName() != null)
				{
					listComputer.add(comp);
				}
				nombreComputer = listComputer.size();
			}
ConnecHikari.getInstance().disconnect();
			return nombreComputer;
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return 0;
	}
	
	public List<Computer> pageComputer(int tri, String colonne, int limit, int offset)
	{
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		String requete;
		try
		{
			Connection preparation = ConnecHikari.getInstance().getConnection();
			if (tri==0||colonne==null)
			{
				requete = PageOrder+" LIMIT ?, ?";
			}
			else if (tri==1)
			{
				requete = PageOrder+" ORDER BY " + colonne + " ASC" + " LIMIT ?, ?";
			}
			else
			{
				requete = PageOrder+" ORDER BY " + colonne + " DESC" + " LIMIT ?, ?";
			}
			PreparedStatement prepare = preparation.prepareStatement(requete);
			prepare.setInt(2, limit);
			prepare.setInt(1, offset);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Computer comp = ComputerMapper.convertRequestByName(resultat);
				if (comp.getName()!= null)
				{
					listComputer.add(comp);
				}
			}
ConnecHikari.getInstance().disconnect();
			return listComputer;
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public Computer findId (long id)
	{
		try
		{
			Connection preparation = ConnecHikari.getInstance().getConnection();
			PreparedStatement prepare = preparation.prepareStatement(FindById) ;
			prepare.setLong(1, id);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Computer comp = ComputerMapper.convertRequestById(resultat);
				if (comp.getName() != null)
				{
					return comp;
				}
				
				else
				{
					return null;
				}
			}
ConnecHikari.getInstance().disconnect();
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return null;
	}
	
	public List<Computer> findName (String recherche)
	{
		ArrayList<Computer> listComputer = new ArrayList<Computer>();
		try
		{
			Connection preparation = ConnecHikari.getInstance().getConnection();
			PreparedStatement prepare = preparation.prepareStatement(FindByName) ;
			recherche = recherche.toLowerCase();
			recherche = "%"+recherche+"%";
			prepare.setString(1,recherche);
			prepare.setString(2,recherche);
			prepare.setString(3,recherche);
			prepare.setString(4,recherche);
			ResultSet resultat = prepare.executeQuery();
			while (resultat.next())
			{ 
				Computer comp = ComputerMapper.convertRequestByName(resultat);
				listComputer.add(comp);
			}
			ConnecHikari.getInstance().disconnect();
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		return listComputer;
	}
	
	public int update(Computer comp)
	{
		int value = 0;
		Connection preparation = ConnecHikari.getInstance().getConnection();
		if (comp.getName() != null)
		{
			try
			{
				PreparedStatement prepare = preparation.prepareStatement(Update);
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
				logger.error("Error request " + e);
			}
		}
		ConnecHikari.getInstance().disconnect();
		return value;
	}
	
	public int delete(long computerId)
	{
		int value = 0;
		Connection preparation = ConnecHikari.getInstance().getConnection();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(DeleteByIdComputer) ;
			prepare.setLong(1, computerId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		ConnecHikari.getInstance().disconnect();
		return value;
	}
	public int deleteComputIdCompany(long companyId)
	{
		int value = 0;
		Connection preparation = ConnecHikari.getInstance().getConnection();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(DeleteByIdCompany) ;
			prepare.setLong(1, companyId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.error("Error request " + e);
		}
		ConnecHikari.getInstance().disconnect();
		return value;
	}
	public int create(Computer comp)
	{
		Connection preparation = ConnecHikari.getInstance().getConnection();
		int value = 0;
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(Insert) ;
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
			logger.error("Error request " + e);
		}
		ConnecHikari.getInstance().disconnect();
		return value;
	}
}