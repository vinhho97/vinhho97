package com.hoangvinh.springboot.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangvinh.springboot.admin.dao.PostDao;
import com.hoangvinh.springboot.admin.entity.Category;
import com.hoangvinh.springboot.admin.entity.Post;
import com.hoangvinh.springboot.admin.model.PostDTO;
import com.hoangvinh.springboot.admin.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	@Override
	public void addPost(PostDTO postDTO) {
		Post post = new Post();
		post.setDescription(postDTO.getDescription());
		post.setCategory(new Category(postDTO.getCategoryID()));
		post.setImageURL(postDTO.getImageURL());

		postDao.addPost(post);
		postDTO.setId(post.getId());
	}

	@Override
	public void updatePost(PostDTO postDTO) {
		Post post = postDao.getPostByID(postDTO.getId());
		if (post != null) {
			post.setDescription(postDTO.getDescription());
			post.setCategory(new Category(postDTO.getCategoryID()));
			post.setImageURL(postDTO.getImageURL());

			postDao.updatePost(post);
		}
	}

	@Override
	public void deletePost(Long id) {
		Post post = postDao.getPostByID(id);
		if (post != null) {
			postDao.deletePost(post);
		}
	}

	@Override
	public PostDTO getPostByID(Long id) {
		Post post = postDao.getPostByID(id);
		if (post != null) {
			PostDTO postDTO = new PostDTO();
			postDTO.setId(post.getId());
			postDTO.setDescription(post.getDescription());
			postDTO.setCategoryID(post.getCategory().getId());
			postDTO.setImageURL(post.getImageURL());

			return postDTO;
		}
		return null;
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> posts = postDao.getAllPost();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		posts.forEach(post -> {
			PostDTO postDTO = new PostDTO();
			postDTO.setId(post.getId());
			postDTO.setDescription(post.getDescription());
			postDTO.setCategoryID(post.getCategory().getId());
			postDTO.setImageURL(post.getImageURL());

			postDTOs.add(postDTO);
		});
		return postDTOs;
	}

	@Override
	public List<PostDTO> getAllPostByCategoryID(Long id) {
		List<Post> posts = postDao.getAllPost();
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		posts.forEach(post -> {
			if (post.getCategory().getId() == id) {
				PostDTO postDTO = new PostDTO();
				postDTO.setId(post.getId());
				postDTO.setDescription(post.getDescription());
				postDTO.setCategoryID(post.getCategory().getId());
				postDTO.setImageURL(post.getImageURL());

				postDTOs.add(postDTO);
			}
		});
		return postDTOs;
	}

}
