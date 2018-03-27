package com.hoangvinh.springboot.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangvinh.springboot.admin.dao.CategoryDao;
import com.hoangvinh.springboot.admin.entity.Category;
import com.hoangvinh.springboot.admin.model.CategoryDTO;
import com.hoangvinh.springboot.admin.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());

		categoryDao.addCategory(category);
		categoryDTO.setId(category.getId());
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryDao.getCategoryByID(categoryDTO.getId());
		if (category != null) {
			category.setName(categoryDTO.getName());

			categoryDao.updateCategory(category);
		}
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryDao.getCategoryByID(id);
		if (category != null) {
			categoryDao.deleteCategory(category);
		}
	}

	@Override
	public CategoryDTO getCategoryByID(Long id) {
		Category category = categoryDao.getCategoryByID(id);
		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());

		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryDao.getAllCategory();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		categories.forEach(category -> {
			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());

			categoryDTOs.add(categoryDTO);
		});
		return categoryDTOs;
	}

}
