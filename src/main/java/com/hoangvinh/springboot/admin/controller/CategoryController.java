package com.hoangvinh.springboot.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hoangvinh.springboot.admin.model.CategoryDTO;
import com.hoangvinh.springboot.admin.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/admin/add-category")
	private String add(Model model) {
		model.addAttribute("addCategoryForm", new CategoryDTO());
		return "admin/web/category/addCategory";
	}

	@PostMapping("/admin/add-category")
	private String add(@ModelAttribute(name = "addCategoryForm") CategoryDTO categoryDTO) {
		categoryService.addCategory(categoryDTO);
		return "redirect:list-category";
	}
	
	@GetMapping("/admin/list-category")
	private String list(Model model){
		model.addAttribute("listCategory", categoryService.getAllCategory());
		return "admin/web/category/listCategory";
	}
	
	@GetMapping("/admin/delete-category/{id}")
	private void delete(@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException{
		categoryService.deleteCategory(id);
		response.sendRedirect("/admin/list-category");
	}
	
	@GetMapping("/admin/update-category/{id}")
	private String update(Model model, @PathVariable(name="id") Long id) {
		model.addAttribute("updateCategoryForm", categoryService.getCategoryByID(id));
		return "admin/web/category/updateCategory";
	}

	@PostMapping("/admin/update-category")
	private void update(@ModelAttribute(name = "updateCategoryForm") CategoryDTO categoryDTO, HttpServletResponse response) throws IOException {
		categoryService.updateCategory(categoryDTO);
		response.sendRedirect("/admin/list-category");
	}
}
