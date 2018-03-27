package com.hoangvinh.springboot.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.hoangvinh.springboot.admin.model.CategoryDTO;
import com.hoangvinh.springboot.admin.service.CategoryService;


public abstract class AbstractController {
	@Autowired
	private CategoryService categoryService;

	public String getCategoryByID(Model model, String viewName) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		model.addAttribute("listCategory", categoryDTOs);
		return viewName;
	}
}
