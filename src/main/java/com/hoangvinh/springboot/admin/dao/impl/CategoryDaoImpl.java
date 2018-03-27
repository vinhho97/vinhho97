package com.hoangvinh.springboot.admin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoangvinh.springboot.admin.dao.CategoryDao;
import com.hoangvinh.springboot.admin.entity.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	@Override
	public void updateCategory(Category category) {
		entityManager.merge(category);
	}

	@Override
	public void deleteCategory(Category category) {
		entityManager.remove(category);
	}

	@Override
	public Category getCategoryByID(Long id) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getAllCategory() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);
		Root<Category> root = criteriaQuery.from(Category.class);

		TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		return typedQuery.getResultList();
	}

}
