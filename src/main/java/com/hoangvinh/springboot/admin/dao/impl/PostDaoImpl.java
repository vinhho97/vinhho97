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

import com.hoangvinh.springboot.admin.dao.PostDao;
import com.hoangvinh.springboot.admin.entity.Post;

@Repository
@Transactional
public class PostDaoImpl implements PostDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void addPost(Post post) {
		entityManager.persist(post);
	}

	@Override
	public void updatePost(Post post) {
		entityManager.merge(post);
	}

	@Override
	public void deletePost(Post post) {
		entityManager.remove(post);
	}

	@Override
	public Post getPostByID(Long id) {
		return entityManager.find(Post.class, id);
	}

	@Override
	public List<Post> getAllPost() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
		Root<Post> root = criteriaQuery.from(Post.class);

		TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		return typedQuery.getResultList();
	}

	@Override
	public List<Post> getAllPostByCategoryID(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
		Root<Post> root = criteriaQuery.from(Post.class);
		criteriaQuery.select(root);
		
		criteriaQuery.where(builder.equal(root.get("category_id"), id));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
}
