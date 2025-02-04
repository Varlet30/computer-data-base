package com.excilys.projet.java.cdb.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.model.Computer;

@Repository
@Transactional
public class ComputerDAO
{
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//public static String CreateComputer = "insert into Computer (name, introduced, discontinued, company.id) values (:name,:introduced,:discontinued,:company.id)";
	//public static String FindComputerId = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=:id";
	//public static String FindComputerName = "SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE :research OR LOWER(company.name) LIKE :research OR introduced LIKE :research OR discontinued LIKE :research;";
	//public static String UpdateComputer = "update Computer set name = :name, introduced = :introduced, discontinued = :discontinued, company.id = :company.id WHERE id = :id";
	//public static String DeleteComputer = "DELETE FROM computer WHERE id = :id";
	public static String DeleteComputerByCompa = "DELETE FROM computer WHERE company_id = :id";
	public static String Select = "from Computer";
	public static String Ascendant = " asc";
	public static String Descendant = " desc";
	public static String Order = " order by ";
	
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;

	public ComputerDAO(EntityManager entityManager)
	{
		super();
		this.entityManager=entityManager;
	}
	
	public List<Computer> allComputer() 
	{
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer", Computer.class);
		return query.getResultList();
	}
	
	public int countComputer() 
	{
		List<Computer> ListComputer = allComputer();
		
		return ListComputer.size();
	}
	
	public List<Computer> pageComputer(int tri, String column, int limit, int offset) 
	{
		String requete;
		
		if (tri == 0||column == ""||column == null) 
		{
			requete = Select;
			
		} 
		else if (tri == 1) 
		{
			requete = Select + Order + column + Ascendant;
				
		} 
		else 
		{
			requete = Select + Order + column + Descendant;
		}
		
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery(requete, Computer.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Computer> computerList = query.getResultList();
    	return computerList;
	}
	
	public Computer findComputerId (long id) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer where id = :id", Computer.class);
	    query.setParameter("id", id);
	    return (Computer)query.getSingleResult();
	}
	
	public List<Computer> findComputerName (String research) 
	{
		research = research.toLowerCase();
		research = "%"+ research +"%";
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer where lower(name) like :research or lower(company.name) like :research or introduced like :research or discontinued like :research", Computer.class);
		query.setParameter("research", research);
		List<Computer> computerList = query.getResultList();
    	return computerList;	
	}
	
	public int updateComputer(Computer comp) 
	{
		Session session = entityManager.unwrap(Session.class);
//		Query<Computer> query = session.createQuery(MODIFIER, Computer.class);
//		query.setParameter("name", comp.getName());
//		query.setParameter("introduced", comp.getIntroduced());
//		query.setParameter("discontinued", comp.getDiscontinued());
//		query.setParameter("company.id", comp.getCompany().getId());
//		query.setParameter("id", comp.getId());
//		int result = query.executeUpdate();
		session.update(comp);
    	return 1;
	}
	
	public int deleteComputer(long id) 
	{
		Session session = entityManager.unwrap(Session.class);
		Computer comp = findComputerId(id);
		session.delete(comp);
		return 1;
	}
	
	public int deleteComputerByCompany(long id) 
	{
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id", id);
		
		return namedParameterJdbcTemplate.update(DeleteComputerByCompa, namedParameters);
	}
	
	public int createComputer(Computer comp) 
	{
		Session session = entityManager.unwrap(Session.class);
		session.save(comp);
    	return 1;
	}
}