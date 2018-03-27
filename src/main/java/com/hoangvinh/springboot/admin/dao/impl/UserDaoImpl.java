package com.hoangvinh.springboot.admin.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoangvinh.springboot.admin.dao.UserDao;
import com.hoangvinh.springboot.admin.entity.UserLocal;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public UserLocal getUserByUsername(String username) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserLocal> criteriaQuery = builder.createQuery(UserLocal.class);
		Root<UserLocal> root = criteriaQuery.from(UserLocal.class);
		criteriaQuery.select(root);
		
//		ParameterExpression<String> parameterExpression = builder.parameter(String.class);
		criteriaQuery.where(builder.equal(root.get("username"), username));
		
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
