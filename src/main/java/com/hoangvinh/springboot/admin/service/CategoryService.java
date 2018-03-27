package com.hoangvinh.springboot.admin.service;

import java.util.List;

import com.hoangvinh.springboot.admin.model.CategoryDTO;

public interface CategoryService {
	void addCategory(CategoryDTO categoryDTO);

	void updateCategory(CategoryDTO categoryDTO);

	void deleteCategory(Long id);

	CategoryDTO getCategoryByID(Long id);

	List<CategoryDTO> getAllCategory();
}
