package com.excilys.projet.java.cdb.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.excilys.projet.java.cdb.model.UserRole;
import com.excilys.projet.java.cdb.model.Users;

@Service
public class UserDAO {

	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;

	public UserDAO(EntityManager entityManager)
	{
		super();
		this.entityManager=entityManager;
	}
	
	private static final String SELECT_USER = "SELECT user FROM Users user WHERE user.username = :username";
	private static final String GET_USER_ROLE = "SELECT userRole FROM UserRole userRole WHERE userRole.user.username = :username";
	
	public List<Users> getUser(String username) {

		Session session = entityManager.unwrap(Session.class);
		Query<Users> query = session.createQuery(SELECT_USER).setParameter("username", username);

		return query.getResultList();
    }

	public void createUser(Users user) {
		Session session = entityManager.unwrap(Session.class);
	    session.save(user);
	}
	
	public void deleteUser(Users user) {
		Session session = entityManager.unwrap(Session.class);
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}

	public List<UserRole> getUserRole(String username) {

		Session session = entityManager.unwrap(Session.class);
	    Query<UserRole> query = session.createQuery(GET_USER_ROLE).setParameter("username", username);

	    return query.getResultList();
	}
}