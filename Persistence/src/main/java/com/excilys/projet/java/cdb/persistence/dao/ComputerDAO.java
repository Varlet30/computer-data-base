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
	
	public static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = :id";
	public static final String FIND_COMPUTER_BY_ID = "from Computer where id = :id";
	public static final String FIND_COMPUTER_BY_NAME = "from Computer where lower(name) like :research or lower(company.name) like :research or introduced like :research or discontinued like :research";
	public static final String SELECT = "from Computer";
	public static final String ASCENDANT = " asc";
	public static final String DESCENDANT = " desc";
	public static final String ORDER = " order by ";
	
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
		Query<Computer> query = session.createQuery(SELECT, Computer.class);
		return query.getResultList();
	}
	
	public int countComputer() 
	{
		List<Computer> listComputer = allComputer();
		
		return listComputer.size();
	}
	
	public List<Computer> pageComputer(int tri, String column, int limit, int offset) 
	{
		String requete;
		
		if (tri == 0||column.equals("")) 
		{
			requete = SELECT;
			
		} 
		else if (tri == 1) 
		{
			requete = SELECT + ORDER + column + ASCENDANT;
				
		} 
		else 
		{
			requete = SELECT + ORDER + column + DESCENDANT;
		}
		
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery(requete, Computer.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		return query.getResultList();
	}
	
	public Computer findComputerId (long id) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery(FIND_COMPUTER_BY_ID, Computer.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public List<Computer> findComputerName (String research) 
	{
		research = research.toLowerCase();
		research = "%"+ research +"%";
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery(FIND_COMPUTER_BY_NAME, Computer.class);
		query.setParameter("research", research);
		return query.getResultList();
	}
	
	public int updateComputer(Computer comp) 
	{
		Session session = entityManager.unwrap(Session.class);
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
		
		return namedParameterJdbcTemplate.update(DELETE_COMPUTER_BY_COMPANY, namedParameters);
	}
	
	public int createComputer(Computer comp) 
	{
		Session session = entityManager.unwrap(Session.class);
		session.save(comp);
		
    	return 1;
	}
}