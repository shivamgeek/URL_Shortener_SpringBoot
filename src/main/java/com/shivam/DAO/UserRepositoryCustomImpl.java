package com.shivam.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.shivam.Entity.User;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<User> loginUser(String email, String password) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from User where email=:email and password=:password");
		query.setParameter("email",email);
		query.setParameter("password", password);
		List<User> list = query.getResultList();
		return list;
	}
	
	
	
}
