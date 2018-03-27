package com.hoangvinh.springboot.admin.service;

import java.util.List;

import com.hoangvinh.springboot.admin.model.PostDTO;

public interface PostService {
	void addPost(PostDTO postDTO);

	void updatePost(PostDTO postDTO);

	void deletePost(Long id);

	PostDTO getPostByID(Long id);

	List<PostDTO> getAllPost();
	
	List<PostDTO> getAllPostByCategoryID(Long id);
}
