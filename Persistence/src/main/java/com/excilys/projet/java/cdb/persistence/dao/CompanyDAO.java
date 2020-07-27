package com.excilys.projet.java.cdb.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import com.excilys.projet.java.cdb.model.Company;

import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CompanyDAO 
{
	
	private ComputerDAO computerDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public static final String DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
	public static final String ALL_COMPANY = "from Company";
	public static final String FIND_COMPANY_BY_ID = "from Company where id = :id";
	public static final String FIND_COMPANY_BY_NAME = "from Company where name = :name";
	
	@PersistenceContext
	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;
	
	public CompanyDAO(EntityManager entityManager)
	{
		super();
		this.entityManager=entityManager;
	}
	
	public List<Company> allCompany() {

		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery(ALL_COMPANY, Company.class);
		return query.getResultList();
	}
	
	public Company findCompany (long id) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery(FIND_COMPANY_BY_ID, Company.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public Company findCompany(String name) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery(FIND_COMPANY_BY_NAME, Company.class);
	    query.setParameter("name", name);
	    return query.getSingleResult();
	}
	
	public int deleteCompany(long companyId) 
	{
		computerDao.deleteComputerByCompany(companyId);
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id", companyId);
		
		return namedParameterJdbcTemplate.update(DELETE_COMPANY,namedParameters);
	}
}